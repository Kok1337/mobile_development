package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.entity.FederalDistrictEntity

interface FederalDistrictStorage {
    fun getAllFederalDistricts(): List<FederalDistrictEntity>
}