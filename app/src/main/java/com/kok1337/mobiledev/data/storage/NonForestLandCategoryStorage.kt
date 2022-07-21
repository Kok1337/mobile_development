package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toNonForestLandCategoryModel
import com.kok1337.mobiledev.domain.model.NonForestLandCategory
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface NonForestLandCategoryStorage {
    fun getAll(): List<NonForestLandCategory>
}

class NonForestLandCategoryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : NonForestLandCategoryStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<NonForestLandCategory> {
        val query = "select * from czl_get_non_forest_land();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toNonForestLandCategoryModel() }
    }
}