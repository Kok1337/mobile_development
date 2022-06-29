package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toLandCategoryModel
import com.kok1337.mobiledev.domain.model.LandCategory
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface LandCategoryStorage {
    fun getAll(): List<LandCategory>
}

class LandCategoryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : LandCategoryStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<LandCategory> {
        val query = "select * from czl_get_land_categories();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toLandCategoryModel() }
    }
}