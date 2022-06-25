package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Section
import java.util.*

interface SectionRepo {
    suspend fun getAllSectionByAreaId(areaId: UUID): List<Section>
}