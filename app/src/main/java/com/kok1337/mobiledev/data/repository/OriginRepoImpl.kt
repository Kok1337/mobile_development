package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.OriginStorage
import com.kok1337.mobiledev.domain.model.Origin
import com.kok1337.mobiledev.domain.repository.OriginRepo
import javax.inject.Inject

class OriginRepoImpl @Inject constructor(
    private val originStorage: OriginStorage,
) : OriginRepo {
    override suspend fun getAllOrigin(): List<Origin> {
        return originStorage.getAll()
    }
}
