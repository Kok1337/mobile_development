package com.kok1337.mobiledev

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _currentTbDestinationLD: MutableLiveData<Int> = MutableLiveData(R.id.tb_rootFragment)
    val currentTbDestinationLD: LiveData<Int> = _currentTbDestinationLD

    fun onOpenMap() { _currentTbDestinationLD.value = R.id.tb_mapFragment }
    fun onOpenCamera() { _currentTbDestinationLD.value = R.id.tb_cameraFragment }
    fun onOpenWorkTypes() { _currentTbDestinationLD.value = R.id.tb_workTypesFragment }
    fun onOpenSettings() { _currentTbDestinationLD.value = R.id.tb_settingsFragment }
}