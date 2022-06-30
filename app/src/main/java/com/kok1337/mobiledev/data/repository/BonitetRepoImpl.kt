package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.BonitetStorage
import com.kok1337.mobiledev.domain.model.Bonitet
import com.kok1337.mobiledev.domain.repository.BonitetRepo
import javax.inject.Inject

class BonitetRepoImpl @Inject constructor(
    private val bonitetStorage: BonitetStorage,
) : BonitetRepo {
    override suspend fun getAllBonitet(): List<Bonitet> {
        return bonitetStorage.getAll()
    }
}
