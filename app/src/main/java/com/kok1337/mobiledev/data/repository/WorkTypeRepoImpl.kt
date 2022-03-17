package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.domain.enum.Work
import com.kok1337.mobiledev.domain.model.WorkType
import com.kok1337.mobiledev.domain.repository.WorkTypeRepo

class WorkTypeRepoImpl : WorkTypeRepo {
    override suspend fun getAllWorkTypes(): List<WorkType> {
        return listOf(
            WorkType(Work.TAXATION, "Тахационное описание"),
            WorkType(Work.TRIAL_AREA, "Временная пробная площадь"),
        )
    }
}