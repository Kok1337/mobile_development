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

class MainViewModel(
    private val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase,
) : ViewModel() {

    private val _currentTbDirectionLD: MutableLiveData<NavDirections> = MutableLiveData()
    val currentTbDirectionLD: LiveData<NavDirections> = _currentTbDirectionLD

    private val _federalDistrictLD: MutableLiveData<List<FederalDistrict>> = MutableLiveData()
    val federalDistrictLD: LiveData<List<FederalDistrict>> = _federalDistrictLD

    fun onOpenMap() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbMapFragment() }
    fun onOpenCamera() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbCameraFragment() }
    fun onOpenWorkTypes() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbWorkTypesFragment() }
    fun onOpenSettings() { _currentTbDirectionLD.value = ToolbarNavGraphDirections.actionGlobalTbSettingsFragment() }

    val userIdLD = MutableLiveData<Int>()

    fun loadFederalDistricts() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _federalDistrictLD.postValue(getAllFederalDistrictUseCase.invoke())
                // return@withContext
            }
        }
    }

    fun getUserId() = async { userIdLD.postValue(getUserIdUseCase.invoke()) }

    fun saveUserId() = async { userIdLD.value?.let { saveUserIdUseCase.invoke(it) } }

    class Factory(
        val getAllFederalDistrictUseCase: GetAllFederalDistrictUseCase,
        private val getUserIdUseCase: GetUserIdUseCase,
        private val saveUserIdUseCase: SaveUserIdUseCase,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(
                getAllFederalDistrictUseCase =  getAllFederalDistrictUseCase,
                getUserIdUseCase = getUserIdUseCase,
                saveUserIdUseCase = saveUserIdUseCase,
            ) as T
        }
    }
}