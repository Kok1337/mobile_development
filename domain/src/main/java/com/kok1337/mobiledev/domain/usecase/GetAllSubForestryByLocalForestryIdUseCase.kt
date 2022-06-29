package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.LocalForestry
import com.kok1337.mobiledev.domain.model.SubForestry
import com.kok1337.mobiledev.domain.repository.SubForestryRepo
import javax.inject.Inject

class GetAllSubForestryByLocalForestryIdUseCase @Inject constructor(
    private val subForestryRepo: SubForestryRepo,
) {
    suspend fun invoke(localForestry: LocalForestry): List<SubForestry> =
        subForestryRepo.getAllSubForestryByLocalForestryId(localForestry.id)
}