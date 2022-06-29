package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.BooleanRowMapper
import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.DictionaryEntity
import com.kok1337.mobiledev.data.entity.TaxSourceEntity
import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.data.mapper.toTaxSourceModel
import com.kok1337.mobiledev.domain.model.TaxSource
import com.kok1337.mobiledev.domain.model.TaxSourceParams
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface TaxSourceStorage {
    fun getAllByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSource>
    fun getAll(): List<TaxSource>
}

class TaxSourceStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TaxSourceStorage {
    private val entityMapper = EntityRowMapper(TaxSourceEntity::class.java)
    override fun getAllByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSource> {
        val query = "select * from czl_get_section_tax_source(?, ?);"
        val list = jdbcTemplate.query(
            query, entityMapper, taxSourceParams.area.id, taxSourceParams.section.name
        )
        return list.map { it.toModel() }
    }

    private val dictionaryMapper = EntityRowMapper(DictionaryEntity::class.java)
    override fun getAll(): List<TaxSource> {
        val query = "select * from czl_get_tax_source();"
        val list = jdbcTemplate.query(query, dictionaryMapper)
        return list.map { it.toTaxSourceModel() }
    }
}