package com.kok1337.mobiledev.data.storage

import com.kok1337.mobiledev.data.database.EntityRowMapper
import com.kok1337.mobiledev.data.entity.AreaEntity
import com.kok1337.mobiledev.data.mapper.toModel
import com.kok1337.mobiledev.domain.model.Area
import com.kok1337.mobiledev.domain.model.AreaParams
import org.springframework.jdbc.core.JdbcTemplate
import javax.inject.Inject

interface AreaStorage {
    fun getAllByAreaParams(areaParams: AreaParams): List<Area>
}

class AreaStorageDbImpl @Inject constructor(
    private val jdbcTemplate: JdbcTemplate,
) : AreaStorage {
    private val mapper = EntityRowMapper(AreaEntity::class.java)
    override fun getAllByAreaParams(areaParams: AreaParams): List<Area> {
        val query = "select * from czl_get_all_areas_find_sections(?, ?, ?, ?, ?);"
        val list = jdbcTemplate.query(query, mapper,
            areaParams.federalDistrict.id,
            areaParams.subjectOfRusFed.id,
            areaParams.forestry.id,
            areaParams.localForestry.id,
            areaParams.subForestry.id,
        )
        return list.map { it.toModel() }
    }
}