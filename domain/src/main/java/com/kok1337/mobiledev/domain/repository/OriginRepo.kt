package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Origin

interface OriginRepo {
    suspend fun getAllOrigin(): List<Origin>
}