package com.kok1337.mobiledev.di

import com.kok1337.mobiledev.presentation.MainActivity
import com.kok1337.mobiledev.presentation.fragment.taxation.AddressFragment
import com.kok1337.mobiledev.presentation.fragment.taxation.TaxTabFragment
import com.kok1337.mobiledev.presentation.fragment.toolbar.WorkTypesFragment
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(workTypesFragment: WorkTypesFragment)
    fun inject(addressFragment: AddressFragment)
    fun inject(taxTabFragment: TaxTabFragment)
}