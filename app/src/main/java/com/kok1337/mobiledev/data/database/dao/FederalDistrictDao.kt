package com.kok1337.mobiledev.data.database.dao

import com.j256.ormlite.dao.BaseDaoImpl
import com.j256.ormlite.support.ConnectionSource
import com.kok1337.mobiledev.data.database.PostgresqlConnectionSource
import com.kok1337.mobiledev.data.model.FederalDistrictEntity

class FederalDistrictDao(connectionSource: ConnectionSource) :
    BaseDaoImpl<FederalDistrictEntity, Int>(connectionSource, FederalDistrictEntity::class.java)