package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.lifecycle.*
import com.kok1337.mobiledev.domain.usecase.GetAllFederalDistrictsUseCase
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.mapper.toItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddressViewModel(
    private val getAllFederalDistrictsUseCase: GetAllFederalDistrictsUseCase,
) : ViewModel() {
    private val _federalDistrictsMutableLiveData: MutableLiveData<List<FederalDistrictItem>> = MutableLiveData()
    val federalDistrictsMutableLiveData: LiveData<List<FederalDistrictItem>> = _federalDistrictsMutableLiveData

    fun getAllFederalDistricts() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                delay(5000)
                _federalDistrictsMutableLiveData.postValue(getAllFederalDistrictsUseCase.invoke().map { it.toItem() })
            }
        }
    }

    class Factory(
        private val getAllFederalDistrictsUseCase: GetAllFederalDistrictsUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddressViewModel(
                getAllFederalDistrictsUseCase = getAllFederalDistrictsUseCase
            ) as T
        }
    }
}