package com.geekbrains.poplibrary.di.modules

import androidx.room.Room
import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.mvp.model.entity.room.Database

import dagger.Module
import dagger.Provides

import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App, @Named("dbName") dbName: String)
        = Room.databaseBuilder(app, Database::class.java, dbName)
        .build()

    @Named("dbName")
    @Provides
    fun dbName() : String = "database.db"
}