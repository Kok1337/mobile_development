package com.kok1337.mobiledev.data.database.annotation

import java.lang.annotation.Inherited

@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class Column(val value: String = "")