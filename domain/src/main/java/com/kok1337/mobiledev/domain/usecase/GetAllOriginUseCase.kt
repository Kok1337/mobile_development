package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Origin
import com.kok1337.mobiledev.domain.repository.OriginRepo
import javax.inject.Inject

class GetAllOriginUseCase @Inject constructor(
    private val originRepo: OriginRepo,
) {
    suspend fun invoke(): List<Origin> = originRepo.getAllOrigin()
}
