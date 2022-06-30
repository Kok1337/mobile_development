package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Ozu
import com.kok1337.mobiledev.domain.repository.OzuRepo
import javax.inject.Inject

class GetAllOzuUseCase @Inject constructor(
    private val ozuRepo: OzuRepo,
) {
    suspend fun invoke(): List<Ozu> = ozuRepo.getAllOzu()
}