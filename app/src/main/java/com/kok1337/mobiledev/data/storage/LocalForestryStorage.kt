package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.LocalForestryDao
import com.kok1337.mobiledev.data.entity.LocalForestryEntity
import javax.inject.Inject

interface LocalForestryStorage {
    fun getAllLocalForestryByForestryId(id: Int): List<LocalForestryEntity>
}

class LocalForestryStorageDbImpl @Inject constructor(
    private val localForestryDao: LocalForestryDao,
) : LocalForestryStorage {
    override fun getAllLocalForestryByForestryId(id: Int): List<LocalForestryEntity> {
        return localForestryDao.findAllByForestryId(id)
    }
}