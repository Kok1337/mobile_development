package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.SectionEntity
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface SectionStorage {
    fun getAllSectionStorageByAreaId(id: UUID): List<SectionEntity>
}

class SectionStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SectionStorage {
    private val mapper = EntityRowMapper(SectionEntity::class.java)
    override fun getAllSectionStorageByAreaId(id: UUID): List<SectionEntity> {
        val query = "select * from czl_get_sections_list(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}