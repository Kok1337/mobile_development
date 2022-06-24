package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.LocalForestryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface LocalForestryDao {
    fun findAllByForestryId(id: Int): List<LocalForestryEntity>
}

class LocalForestryDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : LocalForestryDao {
    private val mapper = EntityRowMapper(LocalForestryEntity::class.java)
    override fun findAllByForestryId(id: Int): List<LocalForestryEntity> {
        val query = "select * from czl_get_all_localforestries(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}