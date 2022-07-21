package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.domain.model.Land
import javax.inject.Inject

interface LandStorage {
    fun getAll(): List<Land>
}

class LandStorageLocalImpl @Inject constructor() : LandStorage {
    override fun getAll(): List<Land> = listOf(
        Land(1, "Лесные", true),
        Land(2, "Не лесные", false),
    )
}