package com.kok1337.mobiledev.domain

import java.lang.IllegalArgumentException

typealias Mapper<Input, Output> = (Input) -> Output

sealed class Result<T> {
    fun <R> map(mapper: Mapper<T, R>? = null): Result<R> = when (this) {
            is PendingResult -> PendingResult()
            is ErrorResult -> ErrorResult(this.exception)
            is SuccessResult -> {
                if (mapper == null) throw IllegalArgumentException("Null mapper for SuccessResult")
                SuccessResult(mapper(this.data))
            }
    }
}

class PendingResult<T> : Result<T>()

class SuccessResult<T>(
    val data: T
) : Result<T>()

class ErrorResult<T>(
    val exception: Exception
) : Result<T>()

fun <T> Result<T>?.takeSuccess(): T? {
    return if (this is SuccessResult)
        this.data
    else
        null
}