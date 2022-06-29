package com.kok1337.mobiledev.domain.usecase

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.domain.repository.SubjectOfRusFedRepo
import javax.inject.Inject

class GetAllSubjectOfRusFedByFederalDistrictIdUseCase @Inject constructor(
    private val subjectOfRusFedRepo: SubjectOfRusFedRepo,
) {
    suspend fun invoke(federalDistrict: FederalDistrict): List<SubjectOfRusFed> =
        subjectOfRusFedRepo.getAllSubjectOfRusFedByFederalDistrictId(federalDistrict.id)
}