package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.Forestry
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.domain.repository.ForestryRepo
import javax.inject.Inject

class GetAllForestryBySubjectOfRusFedUseCase @Inject constructor(
    private val forestryRepo: ForestryRepo,
) {
    suspend fun invoke(subjectOfRusFed: SubjectOfRusFed): List<Forestry> =
        forestryRepo.getAllForestryBySubjectOfRusFedId(subjectOfRusFed.id)
}