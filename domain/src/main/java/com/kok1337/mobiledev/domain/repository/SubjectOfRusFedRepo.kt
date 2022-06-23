package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed

interface SubjectOfRusFedRepo {
    suspend fun getAllSubjectOfRusFedByFederalDistrictId(federalDistrictId: Int): List<SubjectOfRusFed>
}