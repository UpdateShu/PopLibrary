package com.geekbrains.poplibrary

import android.app.Application
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus
import com.geekbrains.poplibrary.navigation.AndroidScreens
import com.geekbrains.poplibrary.navigation.IScreens
import com.geekbrains.poplibrary.ui.network.AndroidNetworkStatus
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
        lateinit var networkStatus : INetworkStatus
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    val screens : IScreens = AndroidScreens()

    override fun onCreate() {
        super.onCreate()
        instance = this
        networkStatus = AndroidNetworkStatus(instance)

        Database.create(this)
    }
}