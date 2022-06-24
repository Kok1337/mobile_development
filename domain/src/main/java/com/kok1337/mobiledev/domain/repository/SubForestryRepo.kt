package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.SubForestry

interface SubForestryRepo {
    suspend fun getAllSubForestryByLocalForestryId(localForestryId: Int): List<SubForestry>
}