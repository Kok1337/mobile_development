package com.kok1337.mobiledev.presentation

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.kok1337.mobiledev.ToolbarNavGraphDirections
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.usecase.GetAllFederalDistrictUseCase
import com.kok1337.mobiledev.domain.usecase.GetUserIdUseCase
import com.kok1337.mobiledev.domain.usecase.SaveUserIdUseCase
import com.kok1337.mobiledev.presentation.util.async
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel(

) : ViewModel() {

    fun saveUserId(saveUserIdUseCase: SaveUserIdUseCase) {
        async {
            saveUserIdUseCase.invoke(2)
        }
    }

    private val _currentTbDirectionLD: MutableLiveData<NavDirections> = MutableLiveData()
    val currentTbDirectionLD: LiveData<NavDirections> = _currentTbDirectionLD

    private val _federalDistrictLD: MutableLiveData<List<FederalDistrict>> = MutableLiveData()
    val federalDistrictLD: LiveData<List<FederalDistrict>> = _federalDistrictLD

    fun onOpenMap() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbMapFragment() }
    fun onOpenCamera() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbCameraFragment() }
    fun onOpenWorkTypes() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbWorkTypesFragment() }
    fun onOpenSettings() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbSettingsFragment() }

    private val _editEnabledLD: MutableLiveData<Boolean> = MutableLiveData(false)
    val editEnabledLD: LiveData<Boolean> = _editEnabledLD
    fun setEditEnabled(enabled: Boolean) { _editEnabledLD.value = enabled }

    class Factory @Inject constructor(

    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(

            ) as T
        }
    }
}