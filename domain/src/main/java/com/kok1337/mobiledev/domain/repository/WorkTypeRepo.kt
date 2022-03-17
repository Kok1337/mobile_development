package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.WorkType

interface WorkTypeRepo {
    suspend fun getAllWorkTypes(): List<WorkType>
}