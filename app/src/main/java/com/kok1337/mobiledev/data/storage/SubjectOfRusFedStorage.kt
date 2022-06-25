package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.SubjectOfRusFedEntity
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface SubjectOfRusFedStorage {
    fun getAllSubjectOfRusFedByFederalDistrictId(id: Int): List<SubjectOfRusFedEntity>
}

class SubjectOfRusFedStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SubjectOfRusFedStorage {
    private val mapper = EntityRowMapper(SubjectOfRusFedEntity::class.java)
    override fun getAllSubjectOfRusFedByFederalDistrictId(id: Int): List<SubjectOfRusFedEntity> {
        val query = "select * from czl_get_all_regions(?);"
        return jdbcTemplate.query(query, mapper, id)
    }
}
