package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.SubjectOfRusFedStorage
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.domain.repository.SubjectOfRusFedRepo

class SubjectOfRusFedRepoImpl(
    private val subjectOfRusFedStorage: SubjectOfRusFedStorage,
) : SubjectOfRusFedRepo {
    override suspend fun getAllSubjectOfRusFedByFederalDistrict(federalDistrict: FederalDistrict): List<SubjectOfRusFed> {
        return subjectOfRusFedStorage.getAllSubjectOfRusFedByFederalDistrict(federalDistrict).map { it.toModel() }
    }
}