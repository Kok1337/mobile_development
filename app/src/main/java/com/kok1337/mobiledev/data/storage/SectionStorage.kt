package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.SectionEntity
import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.domain.model.Section
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface SectionStorage {
    fun getAllByAreaId(id: UUID): List<Section>
}

class SectionStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SectionStorage {
    private val mapper = EntityRowMapper(SectionEntity::class.java)
    override fun getAllByAreaId(id: UUID): List<Section> {
        val query = "select * from czl_get_sections_list(?);"
        val list = jdbcTemplate.query(query, mapper, id)
        return list.map { it.toModel() }
    }
}