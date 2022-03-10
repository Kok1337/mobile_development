package com.kok1337.mobiledev.presentation

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.kok1337.mobiledev.ToolbarNavGraphDirections
import com.kok1337.mobiledev.domain.model.FederalDistrict
import com.kok1337.mobiledev.domain.usecase.LoadAllFederalDistricts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    val loadAllFederalDistricts: LoadAllFederalDistricts
) : ViewModel() {

    private val _currentTbDirectionLD: MutableLiveData<NavDirections> = MutableLiveData()
    val currentTbDirectionLD: LiveData<NavDirections> = _currentTbDirectionLD

    private val _federalDistrictLD: MutableLiveData<List<FederalDistrict>> = MutableLiveData()
    val federalDistrictLD: LiveData<List<FederalDistrict>> = _federalDistrictLD

    fun onOpenMap() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbMapFragment() }
    fun onOpenCamera() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbCameraFragment() }
    fun onOpenWorkTypes() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbWorkTypesFragment() }
    fun onOpenSettings() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbSettingsFragment() }

    fun loadFederalDistricts() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _federalDistrictLD.postValue(loadAllFederalDistricts.invoke())
                // return@withContext
            }
        }
    }

    class Factory(
        val loadAllFederalDistricts: LoadAllFederalDistricts,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            // require(modelClass == MainViewModel::class)
            return MainViewModel(loadAllFederalDistricts) as T
        }
    }
}