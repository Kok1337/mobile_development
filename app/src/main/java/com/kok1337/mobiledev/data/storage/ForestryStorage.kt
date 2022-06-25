package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.ForestryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface ForestryStorage {
    fun getAllForestryBySubjectOfRusFedId(id: Int): List<ForestryEntity>
}

class ForestryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : ForestryStorage {
    private val mapper = EntityRowMapper(ForestryEntity::class.java)
    override fun getAllForestryBySubjectOfRusFedId(id: Int): List<ForestryEntity> {
        val query = "select * from czl_get_all_forestries(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}