package com.geekbrains.poplibrary

import android.app.Application
import com.geekbrains.poplibrary.di.AppComponent
import com.geekbrains.poplibrary.di.DaggerAppComponent

import com.geekbrains.poplibrary.di.modules.AppModule
import com.geekbrains.poplibrary.di.repository.RepositorySubcomponent
import com.geekbrains.poplibrary.di.repository.module.IRepositoryScopeContainer
import com.geekbrains.poplibrary.di.user.UserSubcomponent
import com.geekbrains.poplibrary.di.user.module.IUserScopeContainer

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
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

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }
}