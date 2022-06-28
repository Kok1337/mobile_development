package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.CacheStorage
import com.kok1337.mobiledev.domain.repository.CacheRepo
import javax.inject.Inject

class CacheRepoImpl @Inject constructor(
    private val cacheStorage: CacheStorage,
) : CacheRepo {
    override suspend fun getUserId(): Int {
        return cacheStorage.getUserId()
    }

    override suspend fun saveUserId(userId: Int) {
        cacheStorage.saveUserId(userId)
    }
}