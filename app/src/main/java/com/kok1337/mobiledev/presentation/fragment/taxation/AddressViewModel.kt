package com.kok1337.mobiledev.presentation.fragment.taxation

import android.util.Log
import androidx.lifecycle.*
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.model.SubjectOfRusFed
import com.kok1337.mobiledev.domain.usecase.GetAllFederalDistrictUseCase
import com.kok1337.mobiledev.domain.usecase.GetAllForestryUseCase
import com.kok1337.mobiledev.domain.usecase.GetAllSubjectOfRusFedUseCase
import com.kok1337.mobiledev.presentation.item.FederalDistrictItem
import com.kok1337.mobiledev.presentation.item.ForestryItem
import com.kok1337.mobiledev.presentation.item.SubjectOfRusFedItem
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.model.AddressUIModel
import com.kok1337.mobiledev.presentation.util.async
import javax.inject.Inject

class AddressViewModel(
    private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
    private val getAllSubjectOfRusFedUseCase: GetAllSubjectOfRusFedUseCase,
    private val getAllForestryUseCase: GetAllForestryUseCase,
) : ViewModel() {

    val addressUIModel: LiveData<AddressUIModel>

    private val _federalDistrictSelectedItem = MutableLiveData<FederalDistrictItem?>(null)
    val federalDistrictSelectedItem: LiveData<FederalDistrictItem?> = _federalDistrictSelectedItem
    fun setFederalDistrictSelectedItem(federalDistrictItem: FederalDistrictItem?) { _federalDistrictSelectedItem.value = federalDistrictItem }
    private val _federalDistrictListLiveData = MutableLiveData<List<FederalDistrictItem>>()
    val federalDistrictListLiveData: LiveData<List<FederalDistrictItem>> = _federalDistrictListLiveData
    fun resetFederalDistrictList() { _federalDistrictListLiveData.value = emptyList() }
    fun getAllFederalDistrict() = async { _federalDistrictListLiveData.postValue(getAllFederalDistrictUseCase.invoke().map { it.toItem() }) }

    private val _subjectOfRusFedSelectedItem = MutableLiveData<SubjectOfRusFedItem?>(null)
    val subjectOfRusFedSelectedItem: LiveData<SubjectOfRusFedItem?> = _subjectOfRusFedSelectedItem
    fun setSubjectOfRusFedSelectedItem(subjectOfRusFedItem: SubjectOfRusFedItem?) { _subjectOfRusFedSelectedItem.value = subjectOfRusFedItem }
    private val _subjectOfRusFedListLiveData = MutableLiveData<List<SubjectOfRusFedItem>>()
    val subjectOfRusFedListLiveData: LiveData<List<SubjectOfRusFedItem>> = _subjectOfRusFedListLiveData
    fun resetSubjectOfRusFedList() { _subjectOfRusFedListLiveData.value = emptyList() }
    fun getAllSubjectOfRusFedByFederalDistrict(federalDistrict: FederalDistrict) = async { _subjectOfRusFedListLiveData.postValue(getAllSubjectOfRusFedUseCase.invoke(federalDistrict).map { it.toItem() }) }

    private val _forestrySelectedItem = MutableLiveData<ForestryItem?>(null)
    val forestrySelectedItem: LiveData<ForestryItem?> = _forestrySelectedItem
    fun setForestrySelectedItem(forestryItem: ForestryItem?) { _forestrySelectedItem.value = forestryItem }
    private val _forestryListLiveData = MutableLiveData<List<ForestryItem>>()
    val forestryListLiveData: LiveData<List<ForestryItem>> = _forestryListLiveData
    fun resetForestryList() { _forestryListLiveData.value = emptyList() }
    fun getAllForestryBySubjectOfRusFed(subjectOfRusFed: SubjectOfRusFed) = async { _forestryListLiveData.postValue(getAllForestryUseCase.invoke(subjectOfRusFed).map { it.toItem() }) }

    init {
        addressUIModel = MutableLiveData(AddressUIModel())
        addressUIModel.value?.let {
            _federalDistrictListLiveData.observeForever(it.federalDistrictListObserver)
            _subjectOfRusFedListLiveData.observeForever(it.subjectOfRusFedListObserver)
            _forestryListLiveData.observeForever(it.forestryListObserver)
        }
    }

    class Factory @Inject constructor(
        private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
        private val getAllSubjectOfRusFedUseCase: GetAllSubjectOfRusFedUseCase,
        private val getAllForestryUseCase: GetAllForestryUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddressViewModel(
                getAllFederalDistrictUseCase = getAllFederalDistrictUseCase,
                getAllSubjectOfRusFedUseCase = getAllSubjectOfRusFedUseCase,
                getAllForestryUseCase = getAllForestryUseCase,
            ) as T
        }
    }

    override fun onCleared() {
        addressUIModel.value?.let {
            _federalDistrictListLiveData.removeObserver(it.federalDistrictListObserver)
            _subjectOfRusFedListLiveData.removeObserver(it.subjectOfRusFedListObserver)
            _forestryListLiveData.removeObserver(it.forestryListObserver)
        }
    }
}