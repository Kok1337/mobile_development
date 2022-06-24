package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.AreaEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface AreaDao {
    fun findAllByIds(
        federalDistrictId: Int,
        subjectOfRusFedId: Int,
        forestryId: Int,
        localForestryId: Int,
        subForestryId: Int
    ): List<AreaEntity>
}

class AreaDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : AreaDao {
    private val mapper = EntityRowMapper(AreaEntity::class.java)
    override fun findAllByIds(
        federalDistrictId: Int,
        subjectOfRusFedId: Int,
        forestryId: Int,
        localForestryId: Int,
        subForestryId: Int
    ): List<AreaEntity> {
        val query = "select * from czl_get_all_areas_find_sections(?, ?, ?, ?, ?);"
        return jdbcTemplate.query(query, mapper,
            federalDistrictId,
            subjectOfRusFedId,
            forestryId,
            localForestryId,
            subForestryId
        )
    }
}