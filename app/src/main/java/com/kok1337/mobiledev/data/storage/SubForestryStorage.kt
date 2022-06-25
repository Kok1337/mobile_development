package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.SubForestryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface SubForestryStorage {
    fun getAllSubForestryByLocalForestryId(id: Int): List<SubForestryEntity>
}

class SubForestryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SubForestryStorage {
    private val mapper = EntityRowMapper(SubForestryEntity::class.java)
    override fun getAllSubForestryByLocalForestryId(id: Int): List<SubForestryEntity> {
        val query = "select * from czl_get_all_subforestries(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}