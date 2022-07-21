package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.LandStorage
import com.kok1337.mobiledev.domain.model.Land
import com.kok1337.mobiledev.domain.repository.LandRepo
import javax.inject.Inject

class LandRepoImpl @Inject constructor(
    private val landStorage: LandStorage,
) : LandRepo {
    override suspend fun getAllLand(): List<Land> {
        return landStorage.getAll()
    }
}
