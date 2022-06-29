package com.kok1337.mobiledev.presentation.fragment.taxation

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
    private val checkIsDeletedInfoTaxUseCase: CheckIsDeletedInfoTaxUseCase,
    private val deletedInfoTaxUseCase: DeleteInfoTaxUseCase,
) : ViewModel() {

    fun startLoad() {
        if (federalDistrictSLD.selectedItem == null) getAllFederalDistrict()
    }


    val federalDistrictSLD = SpinnerLiveData<FederalDistrictItem>()
    private fun getAllFederalDistrict() = async {
        federalDistrictSLD.postItems(
            getAllFederalDistrictUseCase.invoke().map { it.toItem() }
        )
    }

    fun onFederalDistrictItemSelected(federalDistrictItem: FederalDistrictItem?) {
        if (!federalDistrictSLD.trySetNewItem(federalDistrictItem)) return
        subjectOfRusFedSLD.setEmptyList()
        federalDistrictItem?.let { getAllSubjectOfRusFedByFederalDistrict(it) }
    }


    val subjectOfRusFedSLD = SpinnerLiveData<SubjectOfRusFedItem>()
    private fun getAllSubjectOfRusFedByFederalDistrict(federalDistrictItem: FederalDistrictItem) = async {
        subjectOfRusFedSLD.postItems(
            getAllSubjectOfRusFedByFederalDistrictUseCase.invoke(federalDistrictItem.toModel())
                .map { it.toItem() }
        )
    }

    fun onSubjectOfRusFedItemSelected(subjectOfRusFedItem: SubjectOfRusFedItem?) {
        if (!subjectOfRusFedSLD.trySetNewItem(subjectOfRusFedItem)) return
        forestrySLD.setEmptyList()
        subjectOfRusFedItem?.let { getAllForestryBySubjectOfRusFed(it) }
    }


    val forestrySLD = SpinnerLiveData<ForestryItem>()
    private fun getAllForestryBySubjectOfRusFed(subjectOfRusFedItem: SubjectOfRusFedItem) = async {
        forestrySLD.postItems(
            getAllForestryBySubjectOfRusFedUseCase.invoke(subjectOfRusFedItem.toModel())
                .map { it.toItem() }
        )
    }

    fun onForestryItemSelected(forestryItem: ForestryItem?) {
        if (!forestrySLD.trySetNewItem(forestryItem)) return
        localForestrySLD.setEmptyList()
        forestryItem?.let { getAllLocalForestryByForestry(it) }
    }


    val localForestrySLD = SpinnerLiveData<LocalForestryItem>()
    private fun getAllLocalForestryByForestry(forestryItem: ForestryItem) = async {
        localForestrySLD.postItems(
            getAllLocalForestryByForestryUseCase.invoke(forestryItem.toModel()).map { it.toItem() }
        )
    }

    fun onLocalForestryItemSelected(localForestryItem: LocalForestryItem?) {
        if (!localForestrySLD.trySetNewItem(localForestryItem)) return
        subForestrySLD.setEmptyList()
        localForestryItem?.let { getAllSubForestryByLocalForestry(it) }
    }


    val subForestrySLD = SpinnerLiveData<SubForestryItem>()
    private fun getAllSubForestryByLocalForestry(localForestryItem: LocalForestryItem) = async {
        subForestrySLD.postItems(
            getAllSubForestryByLocalForestryUseCase.invoke(localForestryItem.toModel())
                .map { it.toItem() }
        )
    }

    fun onSubForestryItemSelected(subForestryItem: SubForestryItem?) {
        if (!subForestrySLD.trySetNewItem(subForestryItem)) return
        areaSLD.setEmptyList()
        subForestryItem?.let { getAllAreaByAreaParams(it) }
    }


    val areaSLD = SpinnerLiveData<AreaItem>()
    private val _allTaxSourceLD = MutableLiveData<List<TaxSourceItem>>(emptyList())
    val allTaxSourceLD: LiveData<List<TaxSourceItem>> = _allTaxSourceLD
    private fun getAllAreaByAreaParams(subForestryItem: SubForestryItem) {
        _allTaxSourceLD.value = emptyList()
        val areaParams = AreaParams(
            federalDistrictSLD.selectedItem!!.toModel(),
            subjectOfRusFedSLD.selectedItem!!.toModel(),
            forestrySLD.selectedItem!!.toModel(),
            localForestrySLD.selectedItem!!.toModel(),
            subForestryItem.toModel()
        )
        async {
            areaSLD.postItems(
                getAllAreaByAreaParamsUseCase.invoke(areaParams).map { it.toItem() }
            )
            _allTaxSourceLD.postValue(getAllTaxSourceUseCase.invoke().map { it.toItem() })
        }
    }

    fun onAreaItemSelected(areaItem: AreaItem?) {
        if (!areaSLD.trySetNewItem(areaItem)) return
        sectionSLD.setEmptyList()
        areaItem?.let { getAllSectionByArea(it) }
    }


    val sectionSLD = SpinnerLiveData<SectionItem>()
    private fun getAllSectionByArea(areaItem: AreaItem) = async {
        sectionSLD.postItems(
            getAllSectionByAreaUseCase.invoke(areaItem.toModel()).map { it.toItem() }
        )
    }

    fun onSectionItemSelected(sectionItem: SectionItem?) {
        if (!sectionSLD.trySetNewItem(sectionItem)) return
        taxSourceSLD.setEmptyList()
        sectionItem?.let { getAllTaxSourceByTaxSourceParams(it) }
    }


    val taxSourceSLD = SpinnerLiveData<TaxSourceItem>()
    private fun getAllTaxSourceByTaxSourceParams(sectionItem: SectionItem) {
        val taxSourceParams = TaxSourceParams(
            areaSLD.selectedItem!!.toModel(),
            sectionItem.toModel()
        )
        async {
            taxSourceSLD.postItems(
                getAllTaxSourceByTaxSourceParamsUseCase.invoke(taxSourceParams)
                    .map { it.toItem() }
            )
        }
    }

    fun onTaxSourceItemSelected(taxSourceItem: TaxSourceItem?) {
        if (!taxSourceSLD.trySetNewItem(taxSourceItem)) return
        taxYearSLD.setEmptyList()
        _isDeletedInfoTaxLD.value = false
        taxSourceItem?.let {
            getAllTaxYearByTaxYearParams(it)
            checkIsDeletedInfoTax(it)
        }
    }


    private val _isDeletedInfoTaxLD = MutableLiveData(false)
    val isDeletedInfoTaxLD: LiveData<Boolean> = _isDeletedInfoTaxLD
    private fun checkIsDeletedInfoTax(taxSourceItem: TaxSourceItem) = async {
        _isDeletedInfoTaxLD.postValue(
            checkIsDeletedInfoTaxUseCase.invoke(taxSourceItem.id)
        )
    }


    val taxYearSLD = SpinnerLiveData<TaxYearItem>()
    private fun getAllTaxYearByTaxYearParams(taxSourceItem: TaxSourceItem) {
        val taxYearParams = TaxYearParams(
            areaSLD.selectedItem!!.toModel(),
            sectionSLD.selectedItem!!.toModel(),
            taxSourceItem.toModel()
        )
        async {
            taxYearSLD.postItems(
                getAllTaxYearByTaxYearParamsUseCase.invoke(taxYearParams).map { it.toItem() }
            )
        }
    }

    fun onTaxYearItemSelected(taxYearItem: TaxYearItem?) {
        taxYearSLD.trySetNewItem(taxYearItem)
    }

    fun deletedInfoTaxByTaxYear(taxYearItem: TaxYearItem) {
        async { deletedInfoTaxUseCase.invoke(taxYearItem.taxId) }
        onSectionItemSelected(null)
    }


    fun trySaveInfoTax(sectionItem: SectionItem, taxSourceItem: TaxSourceItem, year: Int) = async {
        val infoTaxParams = InfoTaxParams(
            sectionItem.toModel(), taxSourceItem.toModel(), year
        )
        if (!checkInfoTaxUseCase.invoke(infoTaxParams)) {
            val infoTaxSaveParams =
                infoTaxParams.toInfoTaxSaveParams(areaSLD.selectedItem!!.toModel())
            saveInfoTaxUseCase.invoke(infoTaxSaveParams)
        }

        if (sectionSLD.tryPostNewItem(sectionItem)) {
            val areaItem = areaSLD.selectedItem!!
            getAllSectionByArea(areaItem)
        }

        if (taxSourceSLD.tryPostNewItem(taxSourceItem)) {
            getAllTaxSourceByTaxSourceParams(sectionItem)
        }

        val taxYearParams = TaxYearParams(
            areaSLD.selectedItem!!.toModel(),
            sectionItem.toModel(),
            taxSourceItem.toModel()
        )
        val taxYearList =
            getAllTaxYearByTaxYearParamsUseCase.invoke(taxYearParams).map { it.toItem() }
        val taxYearSelectedItem = taxYearList.first { it.year == year }
        taxYearSLD.postSelectedItem(taxYearSelectedItem)
        taxYearSLD.postItems(taxYearList)
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
        private val getAllTaxSourceUseCase: GetAllTaxSourceUseCase,
        private val checkInfoTaxUseCase: CheckInfoTaxUseCase,
        private val saveInfoTaxUseCase: SaveInfoTaxUseCase,
        private val checkIsDeletedInfoTaxUseCase: CheckIsDeletedInfoTaxUseCase,
        private val deletedInfoTaxUseCase: DeleteInfoTaxUseCase,
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
                checkIsDeletedInfoTaxUseCase = checkIsDeletedInfoTaxUseCase,
                deletedInfoTaxUseCase = deletedInfoTaxUseCase
            ) as T
        }
    }
}