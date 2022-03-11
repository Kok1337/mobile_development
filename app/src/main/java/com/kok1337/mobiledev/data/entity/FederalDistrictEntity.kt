package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.annotation.Column
import com.kok1337.mobiledev.data.database.annotation.Entity

@Entity
data class FederalDistrictEntity(
    @Column var id: Int? = null,
    @Column var name: String? = null,
)

