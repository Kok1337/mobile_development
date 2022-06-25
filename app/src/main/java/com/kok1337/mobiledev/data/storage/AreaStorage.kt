package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.AreaEntity
import com.kok1337.mobiledev.domain.model.AreaParams
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface AreaStorage {
    fun getAllAreaByAreaParams(areaParams: AreaParams): List<AreaEntity>
}

class AreaStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : AreaStorage {
    private val mapper = EntityRowMapper(AreaEntity::class.java)
    override fun getAllAreaByAreaParams(areaParams: AreaParams): List<AreaEntity> {
        val query = "select * from czl_get_all_areas_find_sections(?, ?, ?, ?, ?);"
        return jdbcTemplate.query(query, mapper,
            areaParams.federalDistrict.id,
            areaParams.subjectOfRusFed.id,
            areaParams.forestry.id,
            areaParams.localForestry.id,
            areaParams.subForestry.id,
        )
    }
}