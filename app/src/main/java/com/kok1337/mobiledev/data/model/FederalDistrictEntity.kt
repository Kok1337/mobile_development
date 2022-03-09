package com.kok1337.mobiledev.data.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(schemaName = "public", tableName = "info_bonitet")
class FederalDistrictEntity {

    @DatabaseField(id = true, columnName = "id")
    var id: Int? = null
        private set

    @DatabaseField(columnName = "rome_name")
    var value: String? = null

    constructor() {}

    constructor(id: Int?, value: String?) {
        this.id = id
        this.value = value
    }
}

/*
@DatabaseTable(schemaName = "public")
class FederalDistrictEntity(

    @DatabaseField(id = true, columnName = "id")


    @DatabaseField(columnName = "name")


)

 */

