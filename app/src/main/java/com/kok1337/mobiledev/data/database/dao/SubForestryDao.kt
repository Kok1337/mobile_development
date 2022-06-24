package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.SubForestryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface SubForestryDao {
    fun findAllByLocalForestryId(id: Int): List<SubForestryEntity>
}

class SubForestryDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SubForestryDao {
    private val mapper = EntityRowMapper(SubForestryEntity::class.java)
    override fun findAllByLocalForestryId(id: Int): List<SubForestryEntity> {
        val query = "select * from czl_get_all_subforestries(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}