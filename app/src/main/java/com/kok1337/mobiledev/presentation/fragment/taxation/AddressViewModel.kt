package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kok1337.mobiledev.domain.model.AreaParams
import com.kok1337.mobiledev.domain.model.TaxSourceParams
import com.kok1337.mobiledev.domain.model.TaxYearParams
import com.kok1337.mobiledev.domain.usecase.*
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.mapper.toModel
import com.kok1337.mobiledev.presentation.util.SpinnerLiveData
import com.kok1337.mobiledev.presentation.util.async
import javax.inject.Inject

class AddressViewModel(
    private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
    private val getAllSubjectOfRusFedByFederalDistrictUseCase: GetAllSubjectOfRusFedByFederalDistrictUseCase,
    private val getAllForestryBySubjectOfRusFedUseCase: GetAllForestryBySubjectOfRusFedUseCase,
    private val getAllLocalForestryByForestryUseCase: GetAllLocalForestryByForestryUseCase,
    private val getAllSubForestryByLocalForestryUseCase: GetAllSubForestryByLocalForestryUseCase,
    private val getAllAreaByAreaParamsUseCase: GetAllAreaByAreaParamsUseCase,
    private val getAllSectionByAreaUseCase: GetAllSectionByAreaUseCase,
    private val getAllTaxSourceByTaxSourceParamsUseCase: GetAllTaxSourceByTaxSourceParamsUseCase,
    private val getAllTaxYearByTaxYearParamsUseCase: GetAllTaxYearByTaxYearParamsUseCase,
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
                    getAllSubjectOfRusFedByFederalDistrictUseCase.invoke(item.toModel()).map { it.toItem() }
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
                    getAllForestryBySubjectOfRusFedUseCase.invoke(item.toModel()).map { it.toItem() }
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
                    getAllLocalForestryByForestryUseCase.invoke(item.toModel()).map { it.toItem() }
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
                    getAllSubForestryByLocalForestryUseCase.invoke(item.toModel()).map { it.toItem() }
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
                    getAllAreaByAreaParamsUseCase.invoke(areaParams).map { it.toItem() }
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
                    getAllSectionByAreaUseCase.invoke(item.toModel()).map { it.toItem() }
                )
            }
        }
    }

    val taxSourceSLD = SpinnerLiveData<TaxSourceItem>()
    fun getAllTaxSourceByTaxSourceParams() {
        taxSourceSLD.setEmptyList()
        async {
            sectionSLD.selectedItem?.let { section ->
                val taxSourceParams = TaxSourceParams(
                    areaSLD.selectedItem!!.toModel(),
                    section.toModel()
                )
                taxSourceSLD.postItems(
                    getAllTaxSourceByTaxSourceParamsUseCase.invoke(taxSourceParams)
                        .map { it.toItem() }
                )
            }
        }
    }

    val taxYearSLD = SpinnerLiveData<TaxYearItem>()

    fun getAllTaxYearByTaxYearParams() {
        taxYearSLD.setEmptyList()
        async {
            taxSourceSLD.selectedItem?.let { taxSourceItem ->
                val taxYearParams = TaxYearParams(
                    areaSLD.selectedItem!!.toModel(),
                    sectionSLD.selectedItem!!.toModel(),
                    taxSourceItem.toModel()
                )
                taxYearSLD.postItems(
                    getAllTaxYearByTaxYearParamsUseCase.invoke(taxYearParams).map { it.toItem() }
                )
            }
        }
    }


    class Factory @Inject constructor(
        private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
        private val getAllSubjectOfRusFedByFederalDistrictUseCase: GetAllSubjectOfRusFedByFederalDistrictUseCase,
        private val getAllForestryBySubjectOfRusFedUseCase: GetAllForestryBySubjectOfRusFedUseCase,
        private val getAllLocalForestryByForestryUseCase: GetAllLocalForestryByForestryUseCase,
        private val getAllSubForestryByLocalForestryUseCase: GetAllSubForestryByLocalForestryUseCase,
        private val getAllAreaByAreaParamsUseCase: GetAllAreaByAreaParamsUseCase,
        private val getAllSectionByAreaUseCase: GetAllSectionByAreaUseCase,
        private val getAllTaxSourceByTaxSourceParamsUseCase: GetAllTaxSourceByTaxSourceParamsUseCase,
        private val getAllTaxYearByTaxYearParamsUseCase: GetAllTaxYearByTaxYearParamsUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddressViewModel(
                getAllFederalDistrictUseCase = getAllFederalDistrictUseCase,
                getAllSubjectOfRusFedByFederalDistrictUseCase = getAllSubjectOfRusFedByFederalDistrictUseCase,
                getAllForestryBySubjectOfRusFedUseCase = getAllForestryBySubjectOfRusFedUseCase,
                getAllLocalForestryByForestryUseCase = getAllLocalForestryByForestryUseCase,
                getAllSubForestryByLocalForestryUseCase = getAllSubForestryByLocalForestryUseCase,
                getAllAreaByAreaParamsUseCase = getAllAreaByAreaParamsUseCase,
                getAllSectionByAreaUseCase = getAllSectionByAreaUseCase,
                getAllTaxSourceByTaxSourceParamsUseCase = getAllTaxSourceByTaxSourceParamsUseCase,
                getAllTaxYearByTaxYearParamsUseCase = getAllTaxYearByTaxYearParamsUseCase,
            ) as T
        }
    }
}