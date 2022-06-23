package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.entity.SubjectOfRusFedEntity
import com.kok1337.mobiledev.data.mapper.SubjectOfRusFedDao
import javax.inject.Inject

interface SubjectOfRusFedStorage {
    fun getAllSubjectOfRusFedByFederalDistrictId(id: Int): List<SubjectOfRusFedEntity>
}

class SubjectOfRusFedStorageDbImpl @Inject constructor(
    private val subjectOfRusFedDao: SubjectOfRusFedDao,
) : SubjectOfRusFedStorage {
    override fun getAllSubjectOfRusFedByFederalDistrictId(id: Int): List<SubjectOfRusFedEntity> {
        return subjectOfRusFedDao.findAllByFederalDistrictId(id)
    }
}
