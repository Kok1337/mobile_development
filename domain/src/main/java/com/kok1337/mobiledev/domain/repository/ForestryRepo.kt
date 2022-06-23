package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.Forestry

interface ForestryRepo {
    suspend fun getAllForestryBySubjectOfRusFedId(subjectOfRusFedId: Int): List<Forestry>
}