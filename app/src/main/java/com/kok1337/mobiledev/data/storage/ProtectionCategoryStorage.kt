package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.mapper.toProtectionCategoryModel
import com.kok1337.mobiledev.domain.model.ProtectionCategory
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface ProtectionCategoryStorage {
    fun getAllByTargetCategoryId(id: Int): List<ProtectionCategory>
}

class ProtectionCategoryStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : ProtectionCategoryStorage {
    private val mapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAllByTargetCategoryId(id: Int): List<ProtectionCategory> {
        val query = "select * from czl_get_protection_category(?);"
        val list = jdbcTemplate.query(query, mapper, id)
        return list.map { it.toProtectionCategoryModel() }
    }
}