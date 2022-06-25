package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.TaxSourceDao
import com.kok1337.mobiledev.data.entity.TaxSourceEntity
import java.util.*
import javax.inject.Inject

interface TaxSourceStorage {
    fun getAllTaxSourceByAreaIdAndSectionName(id: UUID, name: String): List<TaxSourceEntity>
}

class TaxSourceStorageDbImpl @Inject constructor(
    private val taxSourceDao: TaxSourceDao,
) : TaxSourceStorage {
    override fun getAllTaxSourceByAreaIdAndSectionName(id: UUID, name: String): List<TaxSourceEntity> {
        return taxSourceDao.findAllByAreaIdAndSectionName(id, name)
    }
}