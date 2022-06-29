package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toFederalDistrictModel
import com.kok1337.mobiledev.domain.model.FederalDistrict
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface FederalDistrictStorage {
    fun getAll(): List<FederalDistrict>
}

class FederalDistrictStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : FederalDistrictStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<FederalDistrict> {
        val query = "select * from czl_get_all_fo();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toFederalDistrictModel() }
    }
}