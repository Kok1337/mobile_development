package com.kok1337.mobiledev.data.database.dao

import com.j256.ormlite.dao.BaseDaoImpl
import com.kok1337.mobiledev.data.database.PostgresqlConnectionSource
import com.kok1337.mobiledev.data.model.FederalDistrictEntity

class FederalDistrictDao(connectionSource: PostgresqlConnectionSource) :
    BaseDaoImpl<FederalDistrictEntity, Int>(connectionSource, FederalDistrictEntity::class.java)