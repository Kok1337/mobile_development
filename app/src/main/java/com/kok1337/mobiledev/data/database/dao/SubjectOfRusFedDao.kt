package com.kok1337.mobiledev.data.mapper

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.SubjectOfRusFedEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface SubjectOfRusFedDao {
    fun findAllByFederalDistrictId(id: Int): List<SubjectOfRusFedEntity>
}

class SubjectOfRusFedDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SubjectOfRusFedDao {
    private val mapper = EntityRowMapper(SubjectOfRusFedEntity::class.java)
    override fun findAllByFederalDistrictId(id: Int): List<SubjectOfRusFedEntity> {
        val query = "select * from czl_get_all_regions(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}