package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.ForestryDao
import com.kok1337.mobiledev.data.entity.ForestryEntity
import javax.inject.Inject

interface ForestryStorage {
    fun getAllForestryBySubjectOfRusFedId(id: Int): List<ForestryEntity>
}

class ForestryStorageDbImpl @Inject constructor(
    private val forestryDao: ForestryDao,
) : ForestryStorage {
    override fun getAllForestryBySubjectOfRusFedId(id: Int): List<ForestryEntity> {
        return forestryDao.findAllBySubjectOfRusFedId(id)
    }
}