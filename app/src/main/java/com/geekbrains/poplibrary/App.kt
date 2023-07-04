package com.geekbrains.poplibrary

import android.app.Application
import com.geekbrains.poplibrary.di.AppComponent
import com.geekbrains.poplibrary.di.DaggerAppComponent
import com.geekbrains.poplibrary.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}