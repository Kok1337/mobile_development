package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Tlu
import com.kok1337.mobiledev.domain.repository.TluRepo
import javax.inject.Inject

class GetAllTluUseCase @Inject constructor(
    private val tluRepo: TluRepo,
) {
    suspend fun invoke(): List<Tlu> = tluRepo.getAllTlu()
}
