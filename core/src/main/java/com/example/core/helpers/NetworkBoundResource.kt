package com.example.core.helpers

import com.example.core.ui.UiText
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.net.UnknownHostException

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline fetchCache: suspend () -> ResultType,
    crossinline fetchCloud: suspend () -> RequestType,
    crossinline saveCache: suspend (RequestType) -> Unit,
    crossinline shouldFetchCloud: (ResultType) -> Boolean = { true }
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading(isLoading = true))
    val cache = fetchCache()

    emit(Resource.Success(cache))

    if (!shouldFetchCloud(cache)) {
        emit(Resource.Loading(isLoading = false))
        return@flow
    }

    val response = try {
        fetchCloud()
    } catch (e: UnknownHostException) {
        emit(Resource.Error(error = UiText.StringResource(com.example.ui_component.R.string.internet_connection_error)))
        null
    } catch (e: IOException) {
        emit(Resource.Error(error = UiText.StringResource(com.example.ui_component.R.string.api_error)))
        null
    } catch (e: Exception) {
        emit(Resource.Error(error = UiText.StringResource(com.example.ui_component.R.string.unexpected_error)))
        null
    }

    response?.let { data ->
        saveCache(data)

        emit(Resource.Success(fetchCache()))
        emit(Resource.Loading(isLoading = false))
    }
}