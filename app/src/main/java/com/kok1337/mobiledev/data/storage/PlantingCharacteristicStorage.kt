package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.PlantingCharacteristicEntity
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface PlantingCharacteristicStorage {
    fun getPlantingCharacteristicByTaxId(id: UUID): PlantingCharacteristicEntity
}

class PlantingCharacteristicStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : PlantingCharacteristicStorage {
    private val mapper = EntityRowMapper(PlantingCharacteristicEntity::class.java)
    override fun getPlantingCharacteristicByTaxId(id: UUID): PlantingCharacteristicEntity {
        val query = "select * from public.czl_get_tax_glpm_local(?);"
        return jdbcTemplate.queryForObject(query, mapper, id)!!
    }
}