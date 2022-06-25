package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.TaxSourceEntity
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface TaxSourceDao {
    fun findAllByAreaIdAndSectionName(id: UUID, name: String): List<TaxSourceEntity>
}

class TaxSourceDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TaxSourceDao {
    private val mapper = EntityRowMapper(TaxSourceEntity::class.java)
    override fun findAllByAreaIdAndSectionName(id: UUID, name: String): List<TaxSourceEntity> {
        val query = "select * from czl_get_section_tax_source(?, ?);"
        return jdbcTemplate.query(query, mapper, id, name)
    }
}