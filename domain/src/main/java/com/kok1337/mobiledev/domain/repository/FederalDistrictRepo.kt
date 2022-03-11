package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.FederalDistrict

interface FederalDistrictRepo {
    suspend fun getAllFederalDistricts(): List<FederalDistrict>
}