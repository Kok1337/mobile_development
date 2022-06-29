package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.PlantingCharacteristicEntity
import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.domain.model.PlantingCharacteristic
import org.springframework.jdbc.core.JdbcTemplate
import java.util.*
import javax.inject.Inject

interface PlantingCharacteristicStorage {
    fun getByTaxId(id: UUID): PlantingCharacteristic
}

class PlantingCharacteristicStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : PlantingCharacteristicStorage {
    private val mapper = EntityRowMapper(PlantingCharacteristicEntity::class.java)
    override fun getByTaxId(id: UUID): PlantingCharacteristic {
        val query = "select * from public.czl_get_tax_glpm_local(?);"
        val item = jdbcTemplate.queryForObject(query, mapper, id)!!
        return item.toModel()
    }
}