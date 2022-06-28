package com.kok1337.mobiledev.domain.model

data class InfoTaxSaveParams(
    val area: Area,
    val section: Section,
    val taxSource: TaxSource,
    val year: Int,
) {
    // fun toInfoTaxParams(): InfoTaxParams = InfoTaxParams(section, taxSource, year)
}