package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.entity.FederalDistrictEntity

interface FederalDistrictDao {

    fun findAll(): List<FederalDistrictEntity>

}