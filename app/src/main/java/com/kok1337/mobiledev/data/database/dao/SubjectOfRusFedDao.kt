package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.entity.SubjectOfRusFedEntity

interface SubjectOfRusFedDao {
    fun findAllByFederalDistrictId(id: Int): List<SubjectOfRusFedEntity>
}