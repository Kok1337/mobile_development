package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toLocalForestryModel
import com.kok1337.mobiledev.domain.model.LocalForestry
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface LocalForestryStorage {
    fun getAllByForestryId(id: Int): List<LocalForestry>
}

class LocalForestryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : LocalForestryStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAllByForestryId(id: Int): List<LocalForestry> {
        val query = "select * from czl_get_all_localforestries(?);"
        val list = jdbcTemplate.query(query, mapper, id)
        return list.map { it.toLocalForestryModel() }
    }
}