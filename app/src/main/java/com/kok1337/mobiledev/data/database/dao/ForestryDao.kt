package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.ForestryEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface ForestryDao {
    fun findAllBySubjectOfRusFedId(id: Int): List<ForestryEntity>
}

class ForestryDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : ForestryDao {
    private val mapper = EntityRowMapper(ForestryEntity::class.java)
    override fun findAllBySubjectOfRusFedId(id: Int): List<ForestryEntity> {
        val query = "select * from czl_get_all_forestries(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}