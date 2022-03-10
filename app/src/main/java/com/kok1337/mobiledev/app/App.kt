package com.kok1337.mobiledev.app

import android.app.Application
import com.kok1337.mobiledev.di.AppComponent
import com.kok1337.mobiledev.di.AppModule
import com.kok1337.mobiledev.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}