package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.entitymapper.annotation.Column
import com.kok1337.mobiledev.data.database.entitymapper.annotation.Entity
import java.util.*

@Entity
class TaxYearEntity(
    @Column("tax_id") var taxId: UUID? = null,
    @Column("tax_year") var year: Int? = null,
    @Column("draft") var draft: Boolean? = null,
)