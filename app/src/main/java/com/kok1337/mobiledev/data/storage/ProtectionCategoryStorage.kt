package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.ProtectionCategoryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface ProtectionCategoryStorage {
    fun getAllProtectionCategoryByTargetCategoryId(id: Int): List<ProtectionCategoryEntity>
}

class ProtectionCategoryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : ProtectionCategoryStorage {
    private val mapper = EntityRowMapper(ProtectionCategoryEntity::class.java)
    override fun getAllProtectionCategoryByTargetCategoryId(id: Int): List<ProtectionCategoryEntity> {
        val query = "select * from czl_get_protection_category(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}