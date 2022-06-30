package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.OzuStorage
import com.kok1337.mobiledev.domain.model.Ozu
import com.kok1337.mobiledev.domain.repository.OzuRepo
import javax.inject.Inject

class OzuRepoImpl @Inject constructor(
    private val ozuStorage: OzuStorage,
) : OzuRepo {
    override suspend fun getAllOzu(): List<Ozu> {
        return ozuStorage.getAll()
    }
}