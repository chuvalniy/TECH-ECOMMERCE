package com.example.data_user_session.data

interface UserSession {

    fun saveUserId(userId: String)

    fun fetchUserId(): String
}