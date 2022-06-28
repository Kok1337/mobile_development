package com.kok1337.mobiledev.presentation.fragment.taxation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kok1337.mobiledev.domain.model.AreaParams
import com.kok1337.mobiledev.domain.model.InfoTaxParams
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
    private val getAllTaxSourceUseCase: GetAllTaxSourceUseCase,
    private val checkInfoTaxUseCase: CheckInfoTaxUseCase,
    private val saveInfoTaxUseCase: SaveInfoTaxUseCase,
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
                    getAllSubjectOfRusFedByFederalDistrictUseCase.invoke(item.toModel())
                        .map { it.toItem() }
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
                    getAllForestryBySubjectOfRusFedUseCase.invoke(item.toModel())
                        .map { it.toItem() }
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
                    getAllSubForestryByLocalForestryUseCase.invoke(item.toModel())
                        .map { it.toItem() }
                )
            }
        }
    }

    val areaSLD = SpinnerLiveData<AreaItem>()
    fun getAllAreaByAreaParams() {
        areaSLD.setEmptyList()
        _allTaxSourceLD.value = emptyList()
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
            _allTaxSourceLD.postValue(getAllTaxSourceUseCase.invoke().map { it.toItem() })
        }
    }

    val sectionSLD = SpinnerLiveData<SectionItem>()
    fun getAllSectionByArea() {
        sectionSLD.setEmptyList()
        getAllSectionByAreaWithoutReset()
    }

    private fun getAllSectionByAreaWithoutReset() = async {
        areaSLD.selectedItem?.let { item ->
            sectionSLD.postItems(
                getAllSectionByAreaUseCase.invoke(item.toModel()).map { it.toItem() }
            )
        }
    }

    val taxSourceSLD = SpinnerLiveData<TaxSourceItem>()
    fun getAllTaxSourceByTaxSourceParams() {
        taxSourceSLD.setEmptyList()
        getAllTaxSourceByTaxSourceParamsWithoutReset()
    }

    private fun getAllTaxSourceByTaxSourceParamsWithoutReset() = async {
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

    fun trySaveInfoTax(sectionItem: SectionItem, taxSourceItem: TaxSourceItem, year: Int) = async {
        val infoTaxParams = InfoTaxParams(
            sectionItem.toModel(), taxSourceItem.toModel(), year
        )
        if (!checkInfoTaxUseCase.invoke(infoTaxParams)) {
            val infoTaxSaveParams = infoTaxParams.toInfoTaxSaveParams(areaSLD.selectedItem!!.toModel())
            saveInfoTaxUseCase.invoke(infoTaxSaveParams)
        }

        sectionSLD.postSelectedItem(sectionItem)
        getAllSectionByAreaWithoutReset()

        taxSourceSLD.postSelectedItem(taxSourceItem)
        getAllTaxSourceByTaxSourceParamsWithoutReset()

        val taxYearParams = TaxYearParams(
            areaSLD.selectedItem!!.toModel(),
            sectionItem.toModel(),
            taxSourceItem.toModel()
        )
        val taxYearList = getAllTaxYearByTaxYearParamsUseCase.invoke(taxYearParams).map { it.toItem() }
        val taxYearSelectedItem = taxYearList.first { it.year == year}
        taxYearSLD.postSelectedItem(taxYearSelectedItem)
        taxYearSLD.postItems(taxYearList)
    }

    private val _allTaxSourceLD = MutableLiveData<List<TaxSourceItem>>(emptyList())
    val allTaxSourceLD: LiveData<List<TaxSourceItem>> = _allTaxSourceLD

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
        private val getAllTaxSourceUseCase: GetAllTaxSourceUseCase,
        private val checkInfoTaxUseCase: CheckInfoTaxUseCase,
        private val saveInfoTaxUseCase: SaveInfoTaxUseCase,
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
                getAllTaxSourceUseCase = getAllTaxSourceUseCase,
                checkInfoTaxUseCase = checkInfoTaxUseCase,
                saveInfoTaxUseCase = saveInfoTaxUseCase,
            ) as T
        }
    }
}