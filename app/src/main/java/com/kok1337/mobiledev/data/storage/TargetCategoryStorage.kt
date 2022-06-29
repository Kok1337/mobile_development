package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toTargetCategoryModel
import com.kok1337.mobiledev.domain.model.TargetCategory
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface TargetCategoryStorage {
    fun getAll(): List<TargetCategory>
}

class TargetCategoryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TargetCategoryStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<TargetCategory> {
        val query = "select * from czl_get_forest_purpose();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toTargetCategoryModel() }
    }
}