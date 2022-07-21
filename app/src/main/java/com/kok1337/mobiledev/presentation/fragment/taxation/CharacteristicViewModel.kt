package com.kok1337.mobiledev.presentation.fragment.taxation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kok1337.mobiledev.domain.usecase.*
import com.kok1337.mobiledev.presentation.item.*
import com.kok1337.mobiledev.presentation.mapper.toItem
import com.kok1337.mobiledev.presentation.util.SpinnerLiveData
import com.kok1337.mobiledev.presentation.util.async
import javax.inject.Inject

class CharacteristicViewModel(
    private val getAllLandCategoryUseCase: GetAllLandCategoryUseCase,
    private val getAllTargetCategoryUseCase: GetAllTargetCategoryUseCase,
    private val getAllProtectionCategoryByTargetCategoryIdUseCase: GetAllProtectionCategoryByTargetCategoryIdUseCase,
    private val getAllOoptUseCase: GetAllOoptUseCase,
    private val getAllOzuUseCase: GetAllOzuUseCase,
    private val getAllBonitetUseCase: GetAllBonitetUseCase,
    private val getAllTluUseCase: GetAllTluUseCase,
    private val getAllOriginUseCase: GetAllOriginUseCase,
    private val getAllLandUseCase: GetAllLandUseCase,
) : ViewModel() {

    val landCategorySLD = SpinnerLiveData<LandCategoryItem>()
    val targetCategorySLD = SpinnerLiveData<TargetCategoryItem>()
    val protectionCategorySLD = SpinnerLiveData<ProtectionCategoryItem>()
    val ooptSLD = SpinnerLiveData<OoptItem>()
    val ozuSLD = SpinnerLiveData<OzuItem>()
    val bonitetSLD = SpinnerLiveData<BonitetItem>()
    val tluSLD = SpinnerLiveData<TluItem>()
    val originSLD = SpinnerLiveData<OriginItem>()
    val landSLD = SpinnerLiveData<LandItem>()

    private val _isCoveredForestLD = MutableLiveData<Boolean>(false)
    val isCoveredForestLD: LiveData<Boolean> = _isCoveredForestLD

    init {
        getAllLandCategory()
        getAllTargetCategory()
        getAllOopt()
        getAllOzu()
        getAllBonitet()
        getAllTlu()
        getAllOrigin()
        getAllLand()
    }


    private fun getAllLandCategory() = async {
        landCategorySLD.postItems(getAllLandCategoryUseCase.invoke().map { it.toItem() })
    }

    fun onLandCategoryItemSelected(landCategoryItem: LandCategoryItem?) {
        landCategorySLD.trySetNewItem(landCategoryItem)
    }


    private fun getAllTargetCategory() = async {
        targetCategorySLD.postItems(getAllTargetCategoryUseCase.invoke().map { it.toItem() })
    }

    fun onTargetCategoryItemSelected(targetCategoryItem: TargetCategoryItem?) {
        if (!targetCategorySLD.trySetNewItem(targetCategoryItem)) return
        protectionCategorySLD.setEmptyList()
        targetCategoryItem?.let {
            getAllProtectionCategoryByTargetCategory(targetCategoryItem)
        }
    }


    private fun getAllProtectionCategoryByTargetCategory(targetCategoryItem: TargetCategoryItem) =
        async {
            protectionCategorySLD.postItems(
                getAllProtectionCategoryByTargetCategoryIdUseCase.invoke(targetCategoryItem.id)
                    .map { it.toItem() }
            )
        }

    fun onProtectionCategoryItemSelected(protectionCategoryItem: ProtectionCategoryItem?) {
        protectionCategorySLD.trySetNewItem(protectionCategoryItem)
    }


    private fun getAllOopt() = async {
        ooptSLD.postItems(getAllOoptUseCase.invoke().map { it.toItem() })
    }

    fun onOoptItemSelected(ooptItem: OoptItem?) {
        ooptSLD.trySetNewItem(ooptItem)
    }


    private fun getAllOzu() = async {
        ozuSLD.postItems(getAllOzuUseCase.invoke().map { it.toItem() })
    }

    fun onOzuItemSelected(ozuItem: OzuItem?) {
        ozuSLD.trySetNewItem(ozuItem)
    }


    private fun getAllBonitet() = async {
        bonitetSLD.postItems(getAllBonitetUseCase.invoke().map { it.toItem() })
    }

    fun onBonitetItemSelected(bonitetItem: BonitetItem?) {
        bonitetSLD.trySetNewItem(bonitetItem)
    }


    private fun getAllTlu() = async {
        tluSLD.postItems(getAllTluUseCase.invoke().map { it.toItem() })
    }

    fun onTluItemSelected(tluItem: TluItem?) {
        tluSLD.trySetNewItem(tluItem)
    }


    private fun getAllOrigin() = async {
        originSLD.postItems(getAllOriginUseCase.invoke().map { it.toItem() })
    }

    fun onOriginItemSelected(originItem: OriginItem?) {
        originSLD.trySetNewItem(originItem)
    }


    private fun getAllLand() = async {
        landSLD.postItems(getAllLandUseCase.invoke().map { it.toItem() })
    }

    fun onLandItemSelected(landItem: LandItem?) {
        landSLD.trySetNewItem(landItem)
    }


    fun setCoveredForest(value: Boolean) { _isCoveredForestLD.value = value }


    class Factory @Inject constructor(
        private val getAllLandCategoryUseCase: GetAllLandCategoryUseCase,
        private val getAllTargetCategoryUseCase: GetAllTargetCategoryUseCase,
        private val getAllProtectionCategoryByTargetCategoryIdUseCase: GetAllProtectionCategoryByTargetCategoryIdUseCase,
        private val getAllOoptUseCase: GetAllOoptUseCase,
        private val getAllOzuUseCase: GetAllOzuUseCase,
        private val getAllBonitetUseCase: GetAllBonitetUseCase,
        private val getAllTluUseCase: GetAllTluUseCase,
        private val getAllOriginUseCase: GetAllOriginUseCase,
        private val getAllLandUseCase: GetAllLandUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CharacteristicViewModel(
                getAllLandCategoryUseCase = getAllLandCategoryUseCase,
                getAllTargetCategoryUseCase = getAllTargetCategoryUseCase,
                getAllProtectionCategoryByTargetCategoryIdUseCase = getAllProtectionCategoryByTargetCategoryIdUseCase,
                getAllOoptUseCase = getAllOoptUseCase,
                getAllOzuUseCase = getAllOzuUseCase,
                getAllBonitetUseCase = getAllBonitetUseCase,
                getAllTluUseCase = getAllTluUseCase,
                getAllOriginUseCase = getAllOriginUseCase,
                getAllLandUseCase = getAllLandUseCase,
            ) as T
        }
    }
}