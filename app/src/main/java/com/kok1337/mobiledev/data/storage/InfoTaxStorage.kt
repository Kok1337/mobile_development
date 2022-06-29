package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.BooleanRowMapper
import com.kok1337.mobiledev.domain.model.InfoTaxParams
import com.kok1337.mobiledev.domain.model.InfoTaxSaveParams
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface InfoTaxStorage {
    fun isExistsByInfoTaxParams(infoTaxParams: InfoTaxParams): Boolean
    fun saveByInfoTaxSaveParams(infoTaxSaveParams: InfoTaxSaveParams, userId: Int)
    fun deleteById(id: UUID)
    fun isDeletedByTaxSourceId(id: Int): Boolean
}

class InfoTaxStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : InfoTaxStorage {
    override fun isExistsByInfoTaxParams(infoTaxParams: InfoTaxParams): Boolean {
        val mapper = BooleanRowMapper("exists")
        val query =
            "select exists(select 1 from info_tax where section=? and data_source_id=? and forest_inventory_year=?)"
        return jdbcTemplate.queryForObject(
            query, mapper,
            infoTaxParams.section.name, infoTaxParams.taxSource.id, infoTaxParams.year
        )!!
    }

    override fun saveByInfoTaxSaveParams(infoTaxSaveParams: InfoTaxSaveParams, userId: Int) {
        val query =
            "insert into info_tax(id, user_id, is_draft, modification_date, locality_id, section, forest_inventory_year, data_source_id, s) " +
                    "values (uuid_generate_v4(), ?, true, now(), ?, ?, ?, ?, ?);"
        jdbcTemplate.update(
            query,
            userId,
            infoTaxSaveParams.area.id,
            infoTaxSaveParams.section.name,
            infoTaxSaveParams.year,
            infoTaxSaveParams.taxSource.id,
            infoTaxSaveParams.section.s
        )
    }

    override fun deleteById(id: UUID) {
        val query = "update info_tax set data_source_id=9 where id=?"
        jdbcTemplate.update(query, id)
    }

    override fun isDeletedByTaxSourceId(id: Int): Boolean {
        val mapper = BooleanRowMapper("deleted")
        val query = "select id=? as deleted from info_tax_source where id=9"
        return jdbcTemplate.queryForObject(query, mapper, id)!!
    }
}