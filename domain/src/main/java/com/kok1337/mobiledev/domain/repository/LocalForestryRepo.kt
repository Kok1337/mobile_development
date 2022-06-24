package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.LocalForestry

interface LocalForestryRepo {
    suspend fun getAllLocalForestryByForestryId(forestryId: Int): List<LocalForestry>
}