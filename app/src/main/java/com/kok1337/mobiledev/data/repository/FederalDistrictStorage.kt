package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.model.FederalDistrictEntity

interface FederalDistrictStorage {
    suspend fun getAllFederalDistricts(): List<FederalDistrictEntity>
}