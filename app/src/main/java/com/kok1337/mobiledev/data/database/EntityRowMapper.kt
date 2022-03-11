package com.kok1337.mobiledev.data.database

import com.kok1337.mobiledev.data.database.entitymapper.EntityAnnotationRowMapper
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class EntityRowMapper<E>(clazz: Class<E>) : EntityAnnotationRowMapper<E>(clazz), RowMapper<E> {
    override fun mapRow(rs: ResultSet, rowNum: Int): E? = map(rs)
}