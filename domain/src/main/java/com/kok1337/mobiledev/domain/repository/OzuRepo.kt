package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Ozu

interface OzuRepo {
    suspend fun getAllOzu(): List<Ozu>
}