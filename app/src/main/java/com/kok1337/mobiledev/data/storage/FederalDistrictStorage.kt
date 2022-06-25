package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.FederalDistrictEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface FederalDistrictStorage {
    fun getAllFederalDistrict(): List<FederalDistrictEntity>
}

class FederalDistrictStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : FederalDistrictStorage {
    private val mapper = EntityRowMapper(FederalDistrictEntity::class.java)
    override fun getAllFederalDistrict(): List<FederalDistrictEntity> {
        val query = "select * from czl_get_all_fo();"
        return jdbcTemplate.query(query, mapper)
    }
}