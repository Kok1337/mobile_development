package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Oopt

interface OoptRepo {
    suspend fun getAllOopt(): List<Oopt>
}
