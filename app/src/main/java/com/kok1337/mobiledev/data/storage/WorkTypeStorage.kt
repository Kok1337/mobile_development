package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.domain.enum.Work
import com.kok1337.mobiledev.domain.model.WorkType
import javax.inject.Inject

interface WorkTypeStorage {
    fun getAll(): List<WorkType>
}

class WorkTypeStorageLocalImpl @Inject constructor() : WorkTypeStorage {
    override fun getAll(): List<WorkType> {
        return listOf(
            WorkType(Work.TAXATION, "Тахационное описание"),
            WorkType(Work.TRIAL_AREA, "Временная пробная площадь"),
        )
    }
}