package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Bonitet

interface BonitetRepo {
    suspend fun getAllBonitet(): List<Bonitet>
}