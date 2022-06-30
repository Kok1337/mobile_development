package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toBonitetModel
import com.kok1337.mobiledev.domain.model.Bonitet
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface BonitetStorage {
    fun getAll(): List<Bonitet>
}

class BonitetStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : BonitetStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<Bonitet> {
        val query = "select * from czl_get_bonitet();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toBonitetModel() }
    }
}