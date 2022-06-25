package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kok1337.mobiledev.domain.usecase.*
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.mapper.toModel
import com.kok1337.mobiledev.presentation.model.AddressUIModel
import com.kok1337.mobiledev.presentation.util.async
import javax.inject.Inject

class AddressViewModel(
    private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
    private val getAllSubjectOfRusFedUseCase: GetAllSubjectOfRusFedUseCase,
    private val getAllForestryUseCase: GetAllForestryUseCase,
    private val getAllLocalForestryUseCase: GetAllLocalForestryUseCase,
    private val getAllSubForestryUseCase: GetAllSubForestryUseCase,
    private val getAllAreaUseCase: GetAllAreaUseCase,
    private val getAllSectionUseCase: GetAllSectionUseCase,
    private val getAllTaxSourceUseCase: GetAllTaxSourceUseCase,
) : ViewModel() {

    val addressUIModel: LiveData<AddressUIModel>

    private val _federalDistrictSelectedItem = MutableLiveData<FederalDistrictItem?>(null)
    val federalDistrictSelectedItem: LiveData<FederalDistrictItem?> = _federalDistrictSelectedItem
    fun setFederalDistrictSelectedItem(federalDistrictItem: FederalDistrictItem?) {
        _federalDistrictSelectedItem.value = federalDistrictItem
    }

    private val _federalDistrictListLiveData = MutableLiveData<List<FederalDistrictItem>>()
    val federalDistrictListLiveData: LiveData<List<FederalDistrictItem>> =
        _federalDistrictListLiveData

    // fun resetFederalDistrictList() { _federalDistrictListLiveData.value = emptyList() }

    fun getAllFederalDistrict() = async {
        _federalDistrictListLiveData.postValue(
            getAllFederalDistrictUseCase.invoke().map { it.toItem() })
    }

    private val _subjectOfRusFedSelectedItem = MutableLiveData<SubjectOfRusFedItem?>(null)
    val subjectOfRusFedSelectedItem: LiveData<SubjectOfRusFedItem?> = _subjectOfRusFedSelectedItem
    fun setSubjectOfRusFedSelectedItem(subjectOfRusFedItem: SubjectOfRusFedItem?) {
        _subjectOfRusFedSelectedItem.value = subjectOfRusFedItem
    }

    private val _subjectOfRusFedListLiveData = MutableLiveData<List<SubjectOfRusFedItem>>()
    val subjectOfRusFedListLiveData: LiveData<List<SubjectOfRusFedItem>> =
        _subjectOfRusFedListLiveData

    fun resetSubjectOfRusFedList() {
        _subjectOfRusFedListLiveData.value = emptyList()
        _subjectOfRusFedSelectedItem.value = null
    }

    fun getAllSubjectOfRusFedByFederalDistrict(federalDistrict: FederalDistrictItem) = async {
        _subjectOfRusFedListLiveData.postValue(
            getAllSubjectOfRusFedUseCase.invoke(federalDistrict.toModel()).map { it.toItem() })
    }

    private val _forestrySelectedItem = MutableLiveData<ForestryItem?>(null)
    val forestrySelectedItem: LiveData<ForestryItem?> = _forestrySelectedItem
    fun setForestrySelectedItem(forestryItem: ForestryItem?) {
        _forestrySelectedItem.value = forestryItem
    }

    private val _forestryListLiveData = MutableLiveData<List<ForestryItem>>()
    val forestryListLiveData: LiveData<List<ForestryItem>> = _forestryListLiveData
    fun resetForestryList() {
        _forestryListLiveData.value = emptyList()
        _forestrySelectedItem.value = null
    }

    fun getAllForestryBySubjectOfRusFed(subjectOfRusFed: SubjectOfRusFedItem) = async {
        _forestryListLiveData.postValue(
            getAllForestryUseCase.invoke(subjectOfRusFed.toModel()).map { it.toItem() })
    }

    private val _localForestrySelectedItem = MutableLiveData<LocalForestryItem?>(null)
    val localForestrySelectedItem: LiveData<LocalForestryItem?> = _localForestrySelectedItem
    fun setLocalForestrySelectedItem(localForestryItem: LocalForestryItem?) {
        _localForestrySelectedItem.value = localForestryItem
    }

    private val _localForestryListLiveData = MutableLiveData<List<LocalForestryItem>>()
    val localForestryListLiveData: LiveData<List<LocalForestryItem>> = _localForestryListLiveData
    fun resetLocalForestryList() {
        _localForestryListLiveData.value = emptyList()
        _localForestrySelectedItem.value = null
    }

    fun getAllLocalForestryByForestry(forestryItem: ForestryItem) = async {
        _localForestryListLiveData.postValue(
            getAllLocalForestryUseCase.invoke(forestryItem.toModel()).map { it.toItem() })
    }

    private val _subForestrySelectedItem = MutableLiveData<SubForestryItem?>(null)
    val subForestrySelectedItem: LiveData<SubForestryItem?> = _subForestrySelectedItem
    fun setSubForestrySelectedItem(subForestryItem: SubForestryItem?) {
        _subForestrySelectedItem.value = subForestryItem
    }

    private val _subForestryListLiveData = MutableLiveData<List<SubForestryItem>>()
    val subForestryListLiveData: LiveData<List<SubForestryItem>> = _subForestryListLiveData
    fun resetSubForestryList() {
        _subForestryListLiveData.value = emptyList()
        _subForestrySelectedItem.value = null
    }

    fun getAllSubForestryByLocalForestry(localForestryItem: LocalForestryItem) = async {
        _subForestryListLiveData.postValue(
            getAllSubForestryUseCase.invoke(localForestryItem.toModel()).map { it.toItem() })
    }

    private val _areaSelectedItem = MutableLiveData<AreaItem?>(null)
    val areaSelectedItem: LiveData<AreaItem?> = _areaSelectedItem
    fun setAreaSelectedItem(areaItem: AreaItem?) {
        _areaSelectedItem.value = areaItem
    }

    private val _areaListLiveData = MutableLiveData<List<AreaItem>>()
    val areaListLiveData: LiveData<List<AreaItem>> = _areaListLiveData
    fun resetAreaList() {
        _areaListLiveData.value = emptyList()
        _areaSelectedItem.value = null
    }

    fun getAllAreaByAreaParams(areaParams: AreaParamsItem) = async {
        _areaListLiveData.postValue(
            getAllAreaUseCase.invoke(areaParams.toModel()).map { it.toItem() })
    }

    private val _sectionSelectedItem = MutableLiveData<SectionItem?>(null)
    val sectionSelectedItem: LiveData<SectionItem?> = _sectionSelectedItem
    fun setSectionSelectedItem(sectionItem: SectionItem?) {
        _sectionSelectedItem.value = sectionItem
    }

    private val _sectionListLiveData = MutableLiveData<List<SectionItem>>()
    val sectionListLiveData: LiveData<List<SectionItem>> = _sectionListLiveData
    fun resetSectionList() {
        _sectionListLiveData.value = emptyList()
        _sectionSelectedItem.value = null
    }

    fun getAllSectionByArea(area: AreaItem) = async {
        _sectionListLiveData.postValue(
            getAllSectionUseCase.invoke(area.toModel()).map { it.toItem() })
    }

    private val _taxSourceSelectedItem = MutableLiveData<TaxSourceItem?>(null)
    val taxSourceSelectedItem: LiveData<TaxSourceItem?> = _taxSourceSelectedItem
    fun setTaxSourceSelectedItem(taxSourceItem: TaxSourceItem?) {
        _taxSourceSelectedItem.value = taxSourceItem
    }

    private val _taxSourceListLiveData = MutableLiveData<List<TaxSourceItem>>()
    val taxSourceListLiveData: LiveData<List<TaxSourceItem>> = _taxSourceListLiveData
    fun resetTaxSourceList() {
        _taxSourceListLiveData.value = emptyList()
        _taxSourceSelectedItem.value = null
    }

    fun getAllTaxSource(area: AreaItem, section: SectionItem) = async {
        _taxSourceListLiveData.postValue(
            getAllTaxSourceUseCase.invoke(area.toModel(), section.toModel()).map { it.toItem() })
    }


    init {
        addressUIModel = MutableLiveData(AddressUIModel())
        addressUIModel.value?.let {
            _federalDistrictListLiveData.observeForever(it.federalDistrictListObserver)
            _subjectOfRusFedListLiveData.observeForever(it.subjectOfRusFedListObserver)
            _forestryListLiveData.observeForever(it.forestryListObserver)
            _localForestryListLiveData.observeForever(it.localForestryListObserver)
            _subForestryListLiveData.observeForever(it.subForestryListObserver)
            _areaListLiveData.observeForever(it.areaListObserver)
            _sectionListLiveData.observeForever(it.sectionListObserver)
            _taxSourceListLiveData.observeForever(it.taxSourceListObserver)
        }
    }

    class Factory @Inject constructor(
        private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
        private val getAllSubjectOfRusFedUseCase: GetAllSubjectOfRusFedUseCase,
        private val getAllForestryUseCase: GetAllForestryUseCase,
        private val getAllLocalForestryUseCase: GetAllLocalForestryUseCase,
        private val getAllSubForestryUseCase: GetAllSubForestryUseCase,
        private val getAllAreaUseCase: GetAllAreaUseCase,
        private val getAllSectionUseCase: GetAllSectionUseCase,
        private val getAllTaxSourceUseCase: GetAllTaxSourceUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddressViewModel(
                getAllFederalDistrictUseCase = getAllFederalDistrictUseCase,
                getAllSubjectOfRusFedUseCase = getAllSubjectOfRusFedUseCase,
                getAllForestryUseCase = getAllForestryUseCase,
                getAllLocalForestryUseCase = getAllLocalForestryUseCase,
                getAllSubForestryUseCase = getAllSubForestryUseCase,
                getAllAreaUseCase = getAllAreaUseCase,
                getAllSectionUseCase = getAllSectionUseCase,
                getAllTaxSourceUseCase = getAllTaxSourceUseCase,
            ) as T
        }
    }

    override fun onCleared() {
        addressUIModel.value?.let {
            _federalDistrictListLiveData.removeObserver(it.federalDistrictListObserver)
            _subjectOfRusFedListLiveData.removeObserver(it.subjectOfRusFedListObserver)
            _forestryListLiveData.removeObserver(it.forestryListObserver)
            _localForestryListLiveData.removeObserver(it.localForestryListObserver)
            _subForestryListLiveData.removeObserver(it.subForestryListObserver)
            _areaListLiveData.removeObserver(it.areaListObserver)
            _sectionListLiveData.removeObserver(it.sectionListObserver)
            _taxSourceListLiveData.removeObserver(it.taxSourceListObserver)
        }
    }
}