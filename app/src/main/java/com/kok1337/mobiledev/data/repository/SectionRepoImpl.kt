package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.SectionStorage
import com.kok1337.mobiledev.domain.model.Section
import com.kok1337.mobiledev.domain.repository.SectionRepo
import java.util.*
import javax.inject.Inject

class SectionRepoImpl @Inject constructor(
    private val sectionStorage: SectionStorage,
) : SectionRepo {
    override suspend fun getAllSectionByAreaId(areaId: UUID): List<Section> {
        return sectionStorage.getAllSectionStorageByAreaId(areaId).map { it.toModel() }
    }
}