package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.databinding.ObservableInt
import androidx.lifecycle.*
import com.kok1337.mobiledev.domain.usecase.GetAllFederalDistrictUseCase
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.util.async

class AddressViewModel(
    private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
) : ViewModel() {

    private val _federalDistrictsMutableLiveData: MutableLiveData<List<FederalDistrictItem>> = MutableLiveData()
    val federalDistrictsMutableLiveData: LiveData<List<FederalDistrictItem>> = _federalDistrictsMutableLiveData
    private val federalDistrictsMutableLiveDataObserver = Observer<List<FederalDistrictItem>> { federalDistrictsSize.set(it.size) }
    val federalDistrictsSize: ObservableInt = ObservableInt(0)

    init {
        federalDistrictsMutableLiveData.observeForever(federalDistrictsMutableLiveDataObserver)
    }

    override fun onCleared() {
        federalDistrictsMutableLiveData.removeObserver(federalDistrictsMutableLiveDataObserver)
        super.onCleared()
    }

    fun getAllFederalDistrict() {
        async { _federalDistrictsMutableLiveData.postValue(getAllFederalDistrictUseCase.invoke().map { it.toItem() }) }
    }

    class Factory(
        private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddressViewModel(
                getAllFederalDistrictUseCase = getAllFederalDistrictUseCase
            ) as T
        }
    }
}