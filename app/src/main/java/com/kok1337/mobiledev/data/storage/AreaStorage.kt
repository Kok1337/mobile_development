package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.dao.AreaDao
import com.kok1337.mobiledev.data.entity.AreaEntity
import com.kok1337.mobiledev.domain.model.AreaParams
import javax.inject.Inject

interface AreaStorage {
    fun getAllAreaByAreaParams(areaParams: AreaParams): List<AreaEntity>
}

class AreaStorageDbImpl @Inject constructor(
    private val areaDao: AreaDao,
) : AreaStorage {
    override fun getAllAreaByAreaParams(areaParams: AreaParams): List<AreaEntity> {
        return areaDao.findAllByIds(
            areaParams.federalDistrict.id,
            areaParams.subjectOfRusFed.id,
            areaParams.forestry.id,
            areaParams.localForestry.id,
            areaParams.subForestry.id,
        )
    }
}