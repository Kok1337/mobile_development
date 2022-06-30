package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.OriginEntity
import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.domain.model.Origin
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface OriginStorage {
    fun getAll(): List<Origin>
}

class OriginStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : OriginStorage {
    private val mapper = EntityRowMapper(OriginEntity::class.java)
    override fun getAll(): List<Origin> {
        val query = "select * from czl_get_origin();"
        val list = jdbcTemplate.query(query, mapper)
        return list.map { it.toModel() }
    }
}