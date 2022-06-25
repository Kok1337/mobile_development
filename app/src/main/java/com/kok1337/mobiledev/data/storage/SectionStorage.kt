package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.SectionDao
import com.kok1337.mobiledev.data.entity.SectionEntity
import java.util.*
import javax.inject.Inject

interface SectionStorage {
    fun getAllSectionStorageByAreaId(id: UUID): List<SectionEntity>
}

class SectionStorageDbImpl @Inject constructor(
    private val sectionDao: SectionDao,
) : SectionStorage {
    override fun getAllSectionStorageByAreaId(id: UUID): List<SectionEntity> {
        return sectionDao.findAllByAreaId(id)
    }
}