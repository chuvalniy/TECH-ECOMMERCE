package com.example.feature_profile.domain.use_case

import com.example.feature_profile.domain.repository.ProfileRepository

class FetchProfile(
    private val repository: ProfileRepository
) {

    fun execute(userId: String) = repository.fetchData(userId)
}