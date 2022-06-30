package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toTluModel
import com.kok1337.mobiledev.domain.model.Tlu
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface TluStorage {
    fun getAll(): List<Tlu>
}

class TluStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TluStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<Tlu> {
        val query = "select * from czl_get_tlu();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toTluModel() }
    }
}