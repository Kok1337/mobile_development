package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toFederalDistrictModel
import com.kok1337.mobiledev.data.mapper.toSubjectOfRusFedModel
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface SubjectOfRusFedStorage {
    fun getAllByFederalDistrictId(id: Int): List<SubjectOfRusFed>
}

class SubjectOfRusFedStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SubjectOfRusFedStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAllByFederalDistrictId(id: Int): List<SubjectOfRusFed> {
        val query = "select * from czl_get_all_regions(?);"
        val list = jdbcTemplate.query(query, mapper, id)
        return list.map { it.toSubjectOfRusFedModel() }
    }
}
