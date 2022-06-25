package com.kok1337.mobiledev.data.storage

import android.util.Log
import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.TaxSourceEntity
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface TaxSourceStorage {
    fun getAllTaxSourceByAreaIdAndSectionName(id: UUID, name: String): List<TaxSourceEntity>
}

class TaxSourceStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TaxSourceStorage {
    private val mapper = EntityRowMapper(TaxSourceEntity::class.java)
    override fun getAllTaxSourceByAreaIdAndSectionName(id: UUID, name: String): List<TaxSourceEntity> {
        val query = "select * from czl_get_section_tax_source(?, ?);"
        Log.e("TaxSourceStorageDbImpl", "select * from czl_get_section_tax_source($id, $name);")
        return jdbcTemplate.query(query, mapper, id, name)
    }
}