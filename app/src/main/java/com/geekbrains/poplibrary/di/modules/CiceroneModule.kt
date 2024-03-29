package com.geekbrains.poplibrary.di.modules

import com.geekbrains.poplibrary.navigation.AndroidScreens
import com.geekbrains.poplibrary.navigation.IScreens

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class CiceroneModule {

    @Singleton
    @get:Provides
    val cicerone : Cicerone<Router> = Cicerone.create()

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun androidScreens(): IScreens = AndroidScreens()
}