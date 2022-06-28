package com.kok1337.mobiledev.domain.repository

interface CacheRepo {
    suspend fun getUserId(): Int
    suspend fun saveUserId(userId: Int)
}