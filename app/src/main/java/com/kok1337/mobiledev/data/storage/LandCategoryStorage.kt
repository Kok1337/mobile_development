package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.LandCategoryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface LandCategoryStorage {
    fun getAllLandCategory(): List<LandCategoryEntity>
}

class LandCategoryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : LandCategoryStorage {
    private val mapper = EntityRowMapper(LandCategoryEntity::class.java)
    override fun getAllLandCategory(): List<LandCategoryEntity> {
        val query = "select * from czl_get_land_categories();"
        return jdbcTemplate.query(query, mapper)
    }
}