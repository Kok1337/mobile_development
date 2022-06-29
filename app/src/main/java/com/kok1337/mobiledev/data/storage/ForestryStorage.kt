package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toForestryModel
import com.kok1337.mobiledev.domain.model.Forestry
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface ForestryStorage {
    fun getAllBySubjectOfRusFedId(id: Int): List<Forestry>
}

class ForestryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : ForestryStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAllBySubjectOfRusFedId(id: Int): List<Forestry> {
        val query = "select * from czl_get_all_forestries(?);"
        val list = jdbcTemplate.query(query, mapper, id)
        return list.map { it.toForestryModel() }
    }
}