package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.TargetCategoryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface TargetCategoryStorage {
    fun getAllTargetCategory(): List<TargetCategoryEntity>
}

class TargetCategoryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TargetCategoryStorage {
    private val mapper = EntityRowMapper(TargetCategoryEntity::class.java)
    override fun getAllTargetCategory(): List<TargetCategoryEntity> {
        val query = "select * from czl_get_forest_purpose();"
        return jdbcTemplate.query(query, mapper)
    }
}