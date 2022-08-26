package com.example.feature_cart.data.repository

import androidx.room.withTransaction
import com.example.core.helpers.networkBoundResource
import com.example.core.ui.UiText
import com.example.core.utils.Resource
import com.example.feature_cart.R
import com.example.feature_cart.data.local.CartDatabase
import com.example.feature_cart.data.mapper.toCacheDataSource
import com.example.feature_cart.data.mapper.toCloudCartItem
import com.example.feature_cart.data.mapper.toDomainDataSource
import com.example.feature_cart.data.remote.CartFirestore
import com.example.feature_cart.domain.model.DomainDataSource
import com.example.feature_cart.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val db: CartDatabase,
    private val api: CartFirestore
) : CartRepository {

    private val dao = db.dao

    override fun fetchData(userId: String) = networkBoundResource(
        fetchCache = { dao.fetchCache().map { it.toDomainDataSource() } },
        fetchCloud = { api.fetchCloudData(userId) },
        saveCache = { data ->
            db.withTransaction {
                dao.clearCache()
                dao.insertCache(data.map { it.toCacheDataSource() })
            }
        }
    )

    override fun insertData(
        userId: String,
        data: DomainDataSource
    ): Flow<Resource<UiText>> = networkBoundResource {
        api.insertCloudData(userId, data.toCloudCartItem())
        UiText.StringResource(R.string.successfully_added_to_cart)
    }

    override fun deleteData(
        userId: String,
        data: List<DomainDataSource>
    ): Flow<Resource<UiText>> = networkBoundResource {
        api.deleteCloudData(userId, data.map { it.toCloudCartItem() })
        UiText.StringResource(R.string.cart_cleared)
    }

    override fun deleteSingleData(
        userId: String,
        data: DomainDataSource
    ): Flow<Resource<UiText>> = networkBoundResource {
        api.deleteSingleCloudData(userId, data.toCloudCartItem() )
        UiText.StringResource(R.string.item_removed_from_cart)
    }
}