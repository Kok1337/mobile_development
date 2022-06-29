package com.kok1337.mobiledev.domain.model

import java.util.*

data class TaxYear(val taxId: UUID, val year: Int, val draft: Boolean)