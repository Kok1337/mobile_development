package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Land

interface LandRepo {
    suspend fun getAllLand(): List<Land>
}