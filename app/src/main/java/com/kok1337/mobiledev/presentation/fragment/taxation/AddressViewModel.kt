package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kok1337.mobiledev.domain.model.AreaParams
import com.kok1337.mobiledev.domain.usecase.*
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.mapper.toModel
import com.kok1337.mobiledev.presentation.util.SpinnerLiveData
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

    val federalDistrictSLD = SpinnerLiveData<FederalDistrictItem>()
    fun getAllFederalDistrict() = async {
        federalDistrictSLD.postItems(
            getAllFederalDistrictUseCase.invoke().map { it.toItem() }
        )
    }

    val subjectOfRusFedSLD = SpinnerLiveData<SubjectOfRusFedItem>()
    fun getAllSubjectOfRusFedByFederalDistrict() {
        subjectOfRusFedSLD.setEmptyList()
        async {
            federalDistrictSLD.selectedItem?.let { item ->
                subjectOfRusFedSLD.postItems(
                    getAllSubjectOfRusFedUseCase.invoke(item.toModel()).map { it.toItem() }
                )
            }
        }
    }

    val forestrySLD = SpinnerLiveData<ForestryItem>()
    fun getAllForestryBySubjectOfRusFed() {
        forestrySLD.setEmptyList()
        async {
            subjectOfRusFedSLD.selectedItem?.let { item ->
                forestrySLD.postItems(
                    getAllForestryUseCase.invoke(item.toModel()).map { it.toItem() }
                )
            }
        }
    }

    val localForestrySLD = SpinnerLiveData<LocalForestryItem>()
    fun getAllLocalForestryByForestry() {
        localForestrySLD.setEmptyList()
        async {
            forestrySLD.selectedItem?.let { item ->
                localForestrySLD.postItems(
                    getAllLocalForestryUseCase.invoke(item.toModel()).map { it.toItem() }
                )
            }
        }
    }

    val subForestrySLD = SpinnerLiveData<SubForestryItem>()
    fun getAllSubForestryByLocalForestry() {
        subForestrySLD.setEmptyList()
        async {
            localForestrySLD.selectedItem?.let { item ->
                subForestrySLD.postItems(
                    getAllSubForestryUseCase.invoke(item.toModel()).map { it.toItem() }
                )
            }
        }

    }

    val areaSLD = SpinnerLiveData<AreaItem>()
    fun getAllAreaByAreaParams() {
        areaSLD.setEmptyList()
        async {
            subForestrySLD.selectedItem?.let { subForestryItem ->
                val areaParams = AreaParams(
                    federalDistrictSLD.selectedItem!!.toModel(),
                    subjectOfRusFedSLD.selectedItem!!.toModel(),
                    forestrySLD.selectedItem!!.toModel(),
                    localForestrySLD.selectedItem!!.toModel(),
                    subForestryItem.toModel()
                )
                areaSLD.postItems(
                    getAllAreaUseCase.invoke(areaParams).map { it.toItem() }
                )
            }
        }
    }

    val sectionSLD = SpinnerLiveData<SectionItem>()
    fun getAllSectionByArea() {
        sectionSLD.setEmptyList()
        async {
            areaSLD.selectedItem?.let { item ->
                sectionSLD.postItems(
                    getAllSectionUseCase.invoke(item.toModel()).map { it.toItem() }
                )
            }
        }
    }

    val taxSourceSLD = SpinnerLiveData<TaxSourceItem>()
    fun getAllTaxSourceByAreaAndSection() {
        taxSourceSLD.setEmptyList()
        async {
            sectionSLD.selectedItem?.let { section ->
                val area = areaSLD.selectedItem
                taxSourceSLD.postItems(
                    getAllTaxSourceUseCase.invoke(area!!.toModel(), section.toModel())
                        .map { it.toItem() }
                )
            }
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
}