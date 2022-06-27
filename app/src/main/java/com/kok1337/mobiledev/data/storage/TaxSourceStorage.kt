package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.TaxSourceEntity
import com.kok1337.mobiledev.domain.model.TaxSourceParams
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface TaxSourceStorage {
    fun getAllTaxSourceByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSourceEntity>
}

class TaxSourceStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TaxSourceStorage {
    private val mapper = EntityRowMapper(TaxSourceEntity::class.java)
    override fun getAllTaxSourceByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSourceEntity> {
        val query = "select * from czl_get_section_tax_source(?, ?);"
        return jdbcTemplate.query(query, mapper, taxSourceParams.area.id, taxSourceParams.section.name)
    }
}