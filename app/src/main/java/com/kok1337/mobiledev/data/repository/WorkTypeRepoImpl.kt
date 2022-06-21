package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.WorkTypeStorage
import com.kok1337.mobiledev.domain.enum.Work
import com.kok1337.mobiledev.domain.model.WorkType
import com.kok1337.mobiledev.domain.repository.WorkTypeRepo

class WorkTypeRepoImpl(
    private val workTypeStorage: WorkTypeStorage
) : WorkTypeRepo {
    override suspend fun getAllWorkTypes(): List<WorkType> {
        return workTypeStorage.getAllWorkTypes()
    }
}