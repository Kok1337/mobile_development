package com.kok1337.mobiledev.presentation.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.kok1337.mobiledev.R
import com.kok1337.mobiledev.presentation.fragment.toolbar.*

class AppToolbarNavigator(
    private val fragmentManager: FragmentManager,
    private val goBackAction: (() -> Unit)? = null
) : ToolbarNavigator {

    private val mapFragment = MapFragment()
    private val cameraFragment = CameraFragment()
    private val workTypesFragment = WorkTypesFragment()
    private val synchronizationFragment = SynchronizationFragment()
    private val settingsFragment = SettingsFragment()

    init {
        Log.e("AppToolbarNavigator", "init")
    }

    private val toolbarFragmentNameList = listOf(
        getFragmentName(mapFragment),
        getFragmentName(cameraFragment),
        getFragmentName(workTypesFragment),
        getFragmentName(synchronizationFragment),
        getFragmentName(settingsFragment),
    )

    fun initStartFragment(savedInstanceState: Bundle?, fragment: Fragment) {
        if (savedInstanceState == null) {
            fragmentManager.commit {
                add(R.id.nav_host_fragment_activity_main, fragment)
            }
        }
    }

    val upperToolbarFragmentName: String?
        get() {
            var index = fragmentManager.backStackEntryCount
            if (index == 0) return null
            do {
                index--
                val fragmentName = fragmentManager.getBackStackEntryAt(index).name!!
                if (isToolbarFragment(fragmentName)) return fragmentName
            } while (index != 0)
            return null
        }

    fun isWorkTypesFragment(fragmentName: String?): Boolean {
        return fragmentName == getFragmentName(workTypesFragment)
    }

    fun isSettingsFragment(fragmentName: String?): Boolean {
        return fragmentName == getFragmentName(settingsFragment)
    }

    fun isMapFragment(fragmentName: String?): Boolean {
        return fragmentName == getFragmentName(mapFragment)
    }

    fun isCameraFragment(fragmentName: String?): Boolean {
        return fragmentName == getFragmentName(cameraFragment)
    }

    fun isSynchronizationFragment(fragmentName: String?): Boolean {
        return fragmentName == getFragmentName(synchronizationFragment)
    }


    override fun showMapScreen() {
        replaceToolbarFragment(mapFragment)
    }

    override fun showCameraScreen() {
        replaceToolbarFragment(cameraFragment)
    }

    override fun showWorkTypesScreen() {
        val index = getFragmentIndex(workTypesFragment)
        // Если нет WorkType
        if (index == NO_FRAGMENT_INDEX) {
            clearBackStack() // Убираем все 
            launchFragment(workTypesFragment) // Добавляем
            return
        }
        // Если имеется 
        val upOfWorkTypesIndex = getUpOfWorkTypesIndex() // Находим индекс конца WorkTypes''ов
        // Если есть не WorkTypes чистим до верха Иначе сброс в начало
        if (upOfWorkTypesIndex != NO_FRAGMENT_INDEX) clearBackStackToUpOfWorkTypes()
        else fragmentManager.popBackStack(getFragmentName(workTypesFragment), 0)
    }

    override fun showSynchronizationScreen() {
        replaceToolbarFragment(synchronizationFragment)
    }

    override fun showSettingScreen() {
        launchFragment(settingsFragment)
    }

    override fun goBack() {
        goBackAction?.invoke()
    }

    override fun launchFragment(fragment: Fragment) {
        fragmentManager.commit {
            addToBackStack(getFragmentName(fragment))
            replace(R.id.nav_host_fragment_activity_main, fragment)
        }
    }


    private fun replaceToolbarFragment(fragment: Fragment) {
        // Если верхушка стека - ничего
        if (isTopOfStack(fragment)) return
        // Если имеется в стеке - сброс до него (чтобы убрать открытые фрагменты внутри и настройки)
        if (getFragmentIndex(fragment) != NO_FRAGMENT_INDEX) {
            fragmentManager.popBackStack(
                getFragmentName(fragment), 0
            )
            return
        }
        // Если открыт какой то другой фрагмент НЕ WorkTypes
        if (!isTopOfStack(workTypesFragment)) clearBackStackToUpOfWorkTypes() // Чистим до верхнего WorkTypes
        launchFragment(fragment) // Вставляем наш фрагмент
    }


    private fun getFragmentIndex(fragment: Fragment): Int {
        val backStackSize = fragmentManager.backStackEntryCount - 1
        for (index: Int in 0..backStackSize) {
            val fragmentName = fragmentManager.getBackStackEntryAt(index).name!!
            if (fragmentName == getFragmentName(fragment)) return index
        }
        return NO_FRAGMENT_INDEX
    }

    private fun isTopOfStack(fragment: Fragment): Boolean {
        return (getTopOfStackName() == getFragmentName(fragment))
    }

    private fun getTopOfStackName(): String? {
        val index = fragmentManager.backStackEntryCount - 1
        if (index < 0) return null
        return fragmentManager.getBackStackEntryAt(index).name!!
    }

    private fun isToolbarFragment(fragmentName: String): Boolean {
        return toolbarFragmentNameList.contains(fragmentName)
    }

    // Отчистить BackStack полностью
    private fun clearBackStack() {
        val backStackSize = fragmentManager.backStackEntryCount - 1
        for (index: Int in 0..backStackSize) fragmentManager.popBackStack()
    }

    // Отчистить BackStack до верхушки WorkTypes
    private fun clearBackStackToUpOfWorkTypes() {
        val upOfWorkTypesIndex = getUpOfWorkTypesIndex()
        if (upOfWorkTypesIndex == NO_FRAGMENT_INDEX) clearBackStack()
        else repeat(fragmentManager.backStackEntryCount - upOfWorkTypesIndex) {
            fragmentManager.popBackStack()
        }
    }

    // Получить верхушку (то есть следующий toolbar после WorkTypes)
    private fun getUpOfWorkTypesIndex(): Int {
        var wtIndex = getFragmentIndex(workTypesFragment)  // Находим индекс WorkTypes
        if (wtIndex == NO_FRAGMENT_INDEX) return NO_FRAGMENT_INDEX // Если не существует - прекращаем
        wtIndex++ // Берем фрагмент выше
        val backStackSize = fragmentManager.backStackEntryCount - 1
        for (index: Int in wtIndex..backStackSize) {
            val fragmentName = fragmentManager.getBackStackEntryAt(index).name!!
            if (isToolbarFragment(fragmentName)) { // Если это фрагмент toolbar''a 
                return index
            }
        }
        return fragmentManager.backStackEntryCount // Возвращаем верхушку стека
    }

    private fun getFragmentName(fragment: Fragment): String = fragment::class.java.simpleName

    companion object {
        const val NO_FRAGMENT_INDEX = -1
    }
}