package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toOzuModel
import com.kok1337.mobiledev.domain.model.Ozu
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface OzuStorage {
    fun getAll(): List<Ozu>
}

class OzuStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : OzuStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<Ozu> {
        val query = "select * from czl_get_ozu();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toOzuModel() }
    }
}