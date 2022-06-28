package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.repository.CacheRepo
import javax.inject.Inject

class SaveUserIdUseCase @Inject constructor(
    private val cacheRepo: CacheRepo
) {
    suspend fun invoke(userId: Int) = cacheRepo.saveUserId(userId)
}