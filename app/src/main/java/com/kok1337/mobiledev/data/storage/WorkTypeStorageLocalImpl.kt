package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.domain.enum.Work
import com.kok1337.mobiledev.domain.model.WorkType

class WorkTypeStorageLocalImpl : WorkTypeStorage {
    override fun getAllWorkTypes(): List<WorkType> {
        return listOf(
            WorkType(Work.TAXATION, "Тахационное описание"),
            WorkType(Work.TRIAL_AREA, "Временная пробная площадь"),
        )
    }
}