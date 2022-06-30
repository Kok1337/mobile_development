package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toOoptModel
import com.kok1337.mobiledev.domain.model.Oopt
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface OoptStorage {
    fun getAll(): List<Oopt>
}

class OoptStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : OoptStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<Oopt> {
        val query = "select * from czl_get_oopt();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toOoptModel() }
    }
}