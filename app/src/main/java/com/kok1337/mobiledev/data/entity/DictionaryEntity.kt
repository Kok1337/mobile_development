package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.entitymapper.annotation.Column
import com.kok1337.mobiledev.data.database.entitymapper.annotation.Entity

@Entity
open class DictionaryEntity {
    @Column open var id: Int? = null
    @Column open var name: String? = null
}