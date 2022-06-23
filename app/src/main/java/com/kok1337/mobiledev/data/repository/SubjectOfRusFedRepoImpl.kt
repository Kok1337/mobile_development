package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.SubjectOfRusFedStorage
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.domain.repository.SubjectOfRusFedRepo
import javax.inject.Inject

class SubjectOfRusFedRepoImpl @Inject constructor(
    private val subjectOfRusFedStorage: SubjectOfRusFedStorage,
) : SubjectOfRusFedRepo {
    override suspend fun getAllSubjectOfRusFedByFederalDistrictId(federalDistrictId: Int): List<SubjectOfRusFed> {
        return subjectOfRusFedStorage.getAllSubjectOfRusFedByFederalDistrictId(federalDistrictId).map { it.toModel() }
    }
}
