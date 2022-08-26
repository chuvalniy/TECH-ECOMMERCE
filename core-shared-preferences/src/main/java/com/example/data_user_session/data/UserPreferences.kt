package com.example.data_user_session.data

interface UserPreferences {

    fun updateId(userId: String)

    fun fetchId(): String
}