package com.example.feature_details.domain.use_case

import com.example.core.utils.Resource
import com.example.feature_details.domain.repository.DetailsRepository
import com.example.feature_details.presentation.model.DetailsModel
import com.example.feature_favorites.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform

class FetchDetails(
    private val detailsRepository: DetailsRepository,
    private val favoritesRepository: FavoritesRepository
) {
    fun execute(userId: String, id: String): Flow<Resource<DetailsModel>> {
        val detailsData = detailsRepository.fetchData(id)
        val favoritesData = favoritesRepository.isDataExist(userId, id)

        return detailsData.combineTransform(favoritesData) { details, favorites ->
            if (details is Resource.Success && favorites is Resource.Success) {
                var model = DetailsModel()

                details.data?.let { model = model.copy(data = it) }
                favorites.data?.let { model = model.copy(isFavorites = it) }

                emit(Resource.Success(model))
            } else if (details is Resource.Error) {
                emit(Resource.Error(error = details.error))
            } else if (favorites is Resource.Error) {
                emit(Resource.Error(error = favorites.error))
            }
        }
    }
}