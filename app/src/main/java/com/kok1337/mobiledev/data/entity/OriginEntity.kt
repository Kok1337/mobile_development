package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.entitymapper.annotation.Column
import com.kok1337.mobiledev.data.database.entitymapper.annotation.Entity

@Entity
class OriginEntity {
    @Column var id: Int? = null
    @Column var name: String? = null
    @Column var value: Boolean? = null
}
