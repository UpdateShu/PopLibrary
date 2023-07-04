package com.geekbrains.poplibrary

import android.app.Application
import com.geekbrains.poplibrary.di.AppComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}