package com.kok1337.mobiledev.presentation.fragment.toolbar

import android.util.Log
import androidx.lifecycle.*
import com.kok1337.mobiledev.domain.usecase.GetAllWorkTypesUseCase
import com.kok1337.mobiledev.presentation.item.WorkTypeItem
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.util.async
import kotlinx.coroutines.launch

class WorkTypesViewModel(
    private val getAllWorkTypesUseCase: GetAllWorkTypesUseCase,
) : ViewModel() {

    private val _workTypesMutableLiveData: MutableLiveData<List<WorkTypeItem>> = MutableLiveData()
    val workTypesLiveData: LiveData<List<WorkTypeItem>> = _workTypesMutableLiveData

    init {
        Log.e("WorkTypesViewModel", "init")
    }

    fun getAllWorkTypes() = async {
        _workTypesMutableLiveData.postValue(getAllWorkTypesUseCase.invoke().map { it.toItem() })
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("WorkTypesViewModel", "onCleared")
    }

    class Factory(
        val getAllWorkTypesUseCase: GetAllWorkTypesUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WorkTypesViewModel(
                getAllWorkTypesUseCase = getAllWorkTypesUseCase
            ) as T
        }
    }
}