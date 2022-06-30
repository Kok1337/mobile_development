package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.OoptStorage
import com.kok1337.mobiledev.domain.model.Oopt
import com.kok1337.mobiledev.domain.repository.OoptRepo
import javax.inject.Inject

class OoptRepoImpl @Inject constructor(
    private val ooptStorage: OoptStorage,
) : OoptRepo {
    override suspend fun getAllOopt(): List<Oopt> {
        return ooptStorage.getAll()
    }
}
