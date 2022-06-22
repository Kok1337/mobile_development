package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.domain.repository.SubjectOfRusFedRepo

class GetAllSubjectOfRusFed(
    private val subjectOfRusFedRepo: SubjectOfRusFedRepo,
) {
    suspend fun invoke(federalDistrict: FederalDistrict): List<SubjectOfRusFed> = subjectOfRusFedRepo.getAllSubjectOfRusFedByFederalDistrict(federalDistrict)
}