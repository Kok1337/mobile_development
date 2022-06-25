package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.entitymapper.annotation.Column
import com.kok1337.mobiledev.data.database.entitymapper.annotation.Entity

@Entity
class SectionEntity(
    @Column var name: String? = null,
    @Column var s: Double? = null,
)