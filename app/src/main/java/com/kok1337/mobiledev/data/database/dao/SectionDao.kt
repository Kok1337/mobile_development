package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.SectionEntity
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface SectionDao {
    fun findAllByAreaId(id: UUID): List<SectionEntity>
}

class SectionDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SectionDao {
    private val mapper = EntityRowMapper(SectionEntity::class.java)
    override fun findAllByAreaId(id: UUID): List<SectionEntity> {
        val query = "select * from czl_get_sections_list(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}