package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.BooleanRowMapper
import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.TaxSourceAllEntity
import com.kok1337.mobiledev.data.entity.TaxSourceEntity
import com.kok1337.mobiledev.domain.model.TaxSourceParams
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface TaxSourceStorage {
    fun getAllTaxSourceByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSourceEntity>
    fun getAllTaxSource(): List<TaxSourceEntity>
    fun isDeleted(id: Int): Boolean
}

class TaxSourceStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TaxSourceStorage {
    private val taxSourceEntityMapper = EntityRowMapper(TaxSourceEntity::class.java)
    override fun getAllTaxSourceByTaxSourceParams(taxSourceParams: TaxSourceParams): List<TaxSourceEntity> {
        val query = "select * from czl_get_section_tax_source(?, ?);"
        return jdbcTemplate.query(
            query, taxSourceEntityMapper,
            taxSourceParams.area.id, taxSourceParams.section.name
        )
    }

    private val taxSourceAllEntityMapper = EntityRowMapper(TaxSourceAllEntity::class.java)
    override fun getAllTaxSource(): List<TaxSourceEntity> {
        val query = "select * from czl_get_tax_source();"
        return jdbcTemplate.query(query, taxSourceAllEntityMapper).map { it.toTaxSourceEntity() }
    }

    private val booleanRowMapper = BooleanRowMapper("deleted")
    override fun isDeleted(id: Int): Boolean {
        val query = "select id=? as deleted from info_tax_source where id=9"
        return jdbcTemplate.queryForObject(query, booleanRowMapper, id)!!
    }
}