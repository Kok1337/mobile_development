package com.kok1337.mobiledev.domain.model

data class InfoTaxParams(
    val section: Section,
    val taxSource: TaxSource,
    val year: Int,
) {
    fun toInfoTaxSaveParams(area: Area): InfoTaxSaveParams =
        InfoTaxSaveParams(area, section, taxSource, year)
}