package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.SubForestryDao
import com.kok1337.mobiledev.data.entity.SubForestryEntity
import javax.inject.Inject

interface SubForestryStorage {
    fun getAllSubForestryByLocalForestryId(id: Int): List<SubForestryEntity>
}

class SubForestryStorageDbImpl @Inject constructor(
    private val subForestryDao: SubForestryDao,
) : SubForestryStorage {
    override fun getAllSubForestryByLocalForestryId(id: Int): List<SubForestryEntity> {
        return subForestryDao.findAllByLocalForestryId(id)
    }
}