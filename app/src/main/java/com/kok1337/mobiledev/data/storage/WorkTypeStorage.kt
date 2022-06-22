package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.domain.model.WorkType

interface WorkTypeStorage {
    fun getAllWorkType(): List<WorkType>
}