package com.kok1337.mobiledev.domain.repository

import com.kok1337.mobiledev.domain.model.User

interface UserRepo {
    suspend fun getUserIdByUser(user: User): Int
}