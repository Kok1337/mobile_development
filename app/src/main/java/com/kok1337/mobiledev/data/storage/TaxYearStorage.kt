package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.TaxYearEntity
import com.kok1337.mobiledev.domain.model.TaxYearParams
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface TaxYearStorage {
    fun getAllTaxYearByTaxYearParams(taxYearParams: TaxYearParams): List<TaxYearEntity>
}

class TaxYearStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : TaxYearStorage {
    private val mapper = EntityRowMapper(TaxYearEntity::class.java)
    override fun getAllTaxYearByTaxYearParams(taxYearParams: TaxYearParams): List<TaxYearEntity> {
        val query = "select * from czl_get_section_with_year_status(?, ?, ?);"
        return jdbcTemplate.query(query, mapper, taxYearParams.area.id, taxYearParams.section.name, taxYearParams.taxSource.id)
    }
}