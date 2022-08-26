package com.example.feature_profile.domain.use_case

import com.example.feature_profile.domain.model.DomainDataSource
import com.example.feature_profile.domain.repository.ProfileRepository

class UpdateProfile(
    private val repository: ProfileRepository
) {

    fun execute(userId: String, data: DomainDataSource) = repository.updateData(userId, data)
}