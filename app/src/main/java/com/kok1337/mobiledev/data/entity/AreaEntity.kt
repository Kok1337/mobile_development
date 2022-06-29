package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.entitymapper.annotation.Column
import com.kok1337.mobiledev.data.database.entitymapper.annotation.Entity
import java.util.*

@Entity
class AreaEntity {
    @Column var id: UUID? = null
    @Column var name: String? = null
    @Column("find_section") var hasSection: Boolean? = null
}