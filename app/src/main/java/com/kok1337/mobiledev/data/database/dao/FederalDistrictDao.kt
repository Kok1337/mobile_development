package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface FederalDistrictDao {
    fun findAll(): List<FederalDistrictEntity>
}

class FederalDistrictDaoImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : FederalDistrictDao {
    private val mapper = EntityRowMapper(FederalDistrictEntity::class.java)
    override fun findAll(): List<FederalDistrictEntity> {
        val query = "select * from czl_get_all_fo();"
        return jdbcTemplate.query(query, mapper)
    }
}