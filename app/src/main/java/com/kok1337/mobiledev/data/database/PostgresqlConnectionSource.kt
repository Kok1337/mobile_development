package com.kok1337.mobiledev.data.database

import android.content.Context
import com.j256.ormlite.jdbc.JdbcConnectionSource
import java.util.*

class PostgresqlConnectionSource(context: Context) : JdbcConnectionSource() {
    companion object {
        private const val CONFIG_FILE_NAME = "config.properties"
    }

    init {
        val properties = Properties()
        val inStream = context.assets.open(CONFIG_FILE_NAME)
        properties.load(inStream)

        val url = String.format("jdbc:postgresql://%s:%s/%s", properties.getProperty("db_host"),
            properties.getProperty("db_port"), properties.getProperty("db_name")
        )
        val username = properties.getProperty("db_user")
        val password = properties.getProperty("db_password")

        setUrl(url)
        setUsername(username)
        setPassword(password)

        super.initialize()
    }
}