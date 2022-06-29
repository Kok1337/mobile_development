package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.WorkTypeStorage
import com.kok1337.mobiledev.domain.model.WorkType
import com.kok1337.mobiledev.domain.repository.WorkTypeRepo
import javax.inject.Inject

class WorkTypeRepoImpl @Inject constructor(
    private val workTypeStorage: WorkTypeStorage
) : WorkTypeRepo {
    override suspend fun getAllWorkType(): List<WorkType> {
        return workTypeStorage.getAll()
    }
}