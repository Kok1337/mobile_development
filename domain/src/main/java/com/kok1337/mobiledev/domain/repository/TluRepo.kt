package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Tlu

interface TluRepo {
    suspend fun getAllTlu(): List<Tlu>
}
