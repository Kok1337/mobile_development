package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.LocalForestryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface LocalForestryStorage {
    fun getAllLocalForestryByForestryId(id: Int): List<LocalForestryEntity>
}

class LocalForestryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : LocalForestryStorage {
    private val mapper = EntityRowMapper(LocalForestryEntity::class.java)
    override fun getAllLocalForestryByForestryId(id: Int): List<LocalForestryEntity> {
        val query = "select * from czl_get_all_localforestries(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}