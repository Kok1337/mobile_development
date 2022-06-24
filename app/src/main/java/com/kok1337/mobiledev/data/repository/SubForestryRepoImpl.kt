package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.SubForestryStorage
import com.kok1337.mobiledev.domain.model.SubForestry
import com.kok1337.mobiledev.domain.repository.SubForestryRepo
import javax.inject.Inject

class SubForestryRepoImpl @Inject constructor(
    private val subForestryStorage: SubForestryStorage,
) : SubForestryRepo {
    override suspend fun getAllSubForestryByLocalForestryId(localForestryId: Int): List<SubForestry> {
        return subForestryStorage.getAllSubForestryByLocalForestryId(localForestryId).map { it.toModel() }
    }
}
