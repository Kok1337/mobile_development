package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.storage.ForestryStorage
import com.kok1337.mobiledev.domain.model.Forestry
import com.kok1337.mobiledev.domain.repository.ForestryRepo
import javax.inject.Inject

class ForestryRepoImpl @Inject constructor(
    private val forestryStorage: ForestryStorage,
) : ForestryRepo {
    override suspend fun getAllForestryBySubjectOfRusFedId(subjectOfRusFedId: Int): List<Forestry> {
        return forestryStorage.getAllForestryBySubjectOfRusFedId(subjectOfRusFedId).map { it.toModel() }
    }
}