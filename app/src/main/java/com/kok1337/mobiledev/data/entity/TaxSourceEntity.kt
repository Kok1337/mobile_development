package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.entitymapper.annotation.Column

class TaxSourceEntity(
    @Column("tax_sousce_id") var id: Int? = null,
    @Column("sname") var name: String? = null,
)