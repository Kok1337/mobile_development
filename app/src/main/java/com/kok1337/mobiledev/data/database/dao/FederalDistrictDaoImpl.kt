package com.kok1337.mobiledev.data.database.dao

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import org.springframework.jdbc.core.JdbcTemplate

class FederalDistrictDaoImpl(
    private val jdbcTemplate: JdbcTemplate,
) : FederalDistrictDao {

    private val mapper = EntityRowMapper(FederalDistrictEntity::class.java)

    override fun findAll(): List<FederalDistrictEntity> {
        val sql = "select * from czl_get_all_fo();"
        return jdbcTemplate.query(sql, mapper)
    }

}