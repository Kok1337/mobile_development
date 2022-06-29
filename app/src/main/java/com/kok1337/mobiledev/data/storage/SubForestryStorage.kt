package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toSubForestryModel
import com.kok1337.mobiledev.domain.model.SubForestry
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface SubForestryStorage {
    fun getAllSubForestryByLocalForestryId(id: Int): List<SubForestry>
}

class SubForestryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : SubForestryStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAllSubForestryByLocalForestryId(id: Int): List<SubForestry> {
        val query = "select * from czl_get_all_subforestries(?);"
        val list = jdbcTemplate.query(query, mapper, id)
        return list.map { it.toSubForestryModel() }
    }
}