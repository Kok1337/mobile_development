package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Forestry
import com.kok1337.mobiledev.domain.model.LocalForestry
import com.kok1337.mobiledev.domain.repository.LocalForestryRepo
import javax.inject.Inject

class GetAllLocalForestryByForestryUseCase @Inject constructor(
    private val localForestryRepo: LocalForestryRepo,
) {
    suspend fun invoke(forestry: Forestry): List<LocalForestry> =
        localForestryRepo.getAllLocalForestryByForestryId(forestry.id)
}