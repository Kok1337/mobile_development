package com.kok1337.mobiledev.data.repository

import com.kok1337.mobiledev.data.storage.TluStorage
import com.kok1337.mobiledev.domain.model.Tlu
import com.kok1337.mobiledev.domain.repository.TluRepo
import javax.inject.Inject

class TluRepoImpl @Inject constructor(
    private val tluStorage: TluStorage,
) : TluRepo {
    override suspend fun getAllTlu(): List<Tlu> {
        return tluStorage.getAll()
    }
}
