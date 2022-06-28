package com.kok1337.mobiledev.data.database

import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet

class BooleanRowMapper(private val columnName: String) : RowMapper<Boolean> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Boolean {
        return rs.getBoolean(columnName)
    }
}