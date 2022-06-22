package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.entity.SubjectOfRusFedEntity
import com.kok1337.mobiledev.domain.model.FederalDistrict

interface SubjectOfRusFedStorage {
    fun getAllSubjectOfRusFedByFederalDistrict(federalDistrict: FederalDistrict): List<SubjectOfRusFedEntity>
}