package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Oopt
import com.kok1337.mobiledev.domain.repository.OoptRepo
import javax.inject.Inject

class GetAllOoptUseCase @Inject constructor(
    private val ooptRepo: OoptRepo,
) {
    suspend fun invoke(): List<Oopt> = ooptRepo.getAllOopt()
}
