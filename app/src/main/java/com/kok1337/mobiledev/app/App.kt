package com.kok1337.mobiledev.app

import android.app.Application
import com.kok1337.mobiledev.di.appModule
import com.kok1337.mobiledev.di.dataModule
import com.kok1337.mobiledev.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                appModule,
                domainModule,
                dataModule
            ))
        }
    }
}