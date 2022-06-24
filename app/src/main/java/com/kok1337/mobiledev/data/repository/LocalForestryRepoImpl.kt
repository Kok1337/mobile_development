package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.LocalForestryStorage
import com.kok1337.mobiledev.domain.model.LocalForestry
import com.kok1337.mobiledev.domain.repository.LocalForestryRepo
import javax.inject.Inject

class LocalForestryRepoImpl @Inject constructor(
    private val localForestryStorage: LocalForestryStorage,
) : LocalForestryRepo {
    override suspend fun getAllLocalForestryByForestryId(forestryId: Int): List<LocalForestry> {
        return localForestryStorage.getAllLocalForestryByForestryId(forestryId).map { it.toModel() }
    }
}