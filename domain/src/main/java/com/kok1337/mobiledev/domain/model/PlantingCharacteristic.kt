package com.kok1337.mobiledev.domain.model

import java.util.*

data class PlantingCharacteristic(
    val id: UUID,
    val s: Double,

    var landCatId: Int?, // код категории земель
    var forestPurposeId: Int?, // Целевое назначение лесов
    var protectionCategoryId: Int?, // Категория защитности
    var ooptId: Int?,
    var oopt: String,
    var ozuId: Int?,
    var ozu: String,
    var bonitetId: Int?,
    var forestType: String,
    var tluId: Int?,
    var isNatural: Boolean?, // происхождение насаждения
    var nonForestLandId: Int?, // Код категории нелесных земель (Не лесные земли)
    var unforestedLandId: Int?, // Код категории не покрытых лесной растительностью земель (Лесные земли)

    var stockDead: Double?, // запас сухостоя, кбм на выдел
    var stockOpenStand: Double?, // запас редин, кбм на выдел
    var stockSingleTree: Double?, // запас единичных деревьев, кбм на выдел
    var stockFellingDebris: Double?, // запас захламленности общий, кбм на выдел
    var stockLiquidDebris: Double?, // запас ликвидной захламленности, кбм на выдел
    var stockSection: Double?,
)