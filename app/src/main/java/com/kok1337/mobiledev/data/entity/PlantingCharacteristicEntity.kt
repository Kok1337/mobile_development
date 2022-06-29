package com.kok1337.mobiledev.data.entity

import com.kok1337.mobiledev.data.database.entitymapper.annotation.Column
import com.kok1337.mobiledev.data.database.entitymapper.annotation.Entity
import java.util.*

@Entity
class PlantingCharacteristicEntity {
    @Column("tax_id") var id: UUID? = null
    @Column("s") var s: Double? = null
    @Column("land_cat_id") var landCatId: Int? = null
    @Column("forest_purpose_id") var forestPurposeId: Int? = null
    @Column("protection_category_id") var protectionCategoryId: Int? = null
    @Column("oopt_id") var ooptId: Int? = null
    @Column("oopt") var oopt: String? = null
    @Column("ozu_id") var ozuId: Int? = null
    @Column("ozu") var ozu: String? = null
    @Column("bonitet_id") var bonitetId: Int? = null
    @Column("forest_type") var forestType: String? = null
    @Column("tlu_id") var tluId: Int? = null
    @Column("is_natural") var isNatural: Boolean? = null
    @Column("non_forest_land_id") var nonForestLandId: Int? = null
    @Column("unforested_land_id") var unforestedLandId: Int? = null
    @Column("stock_dead") var stockDead: Double? = null
    @Column("stock_open_stand") var stockOpenStand: Double? = null
    @Column("stock_single_tree") var stockSingleTree: Double? = null
    @Column("stock_felling_debris") var stockFellingDebris: Double? = null
    @Column("stock_liquid_debris") var stockLiquidDebris: Double? = null
    @Column("stock_section") var stockSection: Double? = null
}