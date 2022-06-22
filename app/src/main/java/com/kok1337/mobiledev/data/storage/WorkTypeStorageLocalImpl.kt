package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.domain.enum.Work
import com.kok1337.mobiledev.domain.model.WorkType
import javax.inject.Inject

class WorkTypeStorageLocalImpl @Inject constructor() : WorkTypeStorage {
    override fun getAllWorkType(): List<WorkType> {
        return listOf(
            WorkType(Work.TAXATION, "Тахационное описание"),
            WorkType(Work.TRIAL_AREA, "Временная пробная площадь"),
        )
    }
}