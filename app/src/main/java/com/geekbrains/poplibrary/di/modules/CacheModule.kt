package com.geekbrains.poplibrary.di.modules

import androidx.room.Room
import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.mvp.model.cache.IGithubRepositoriesCache
import com.geekbrains.poplibrary.mvp.model.cache.IGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.cache.RoomGithubRepositoriesCache
import com.geekbrains.poplibrary.mvp.model.cache.RoomGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun database(app: App, @Named("dbName") dbName: String)
        = Room.databaseBuilder(app, Database::class.java, dbName)
        .build()

    @Named("dbName")
    @Provides
    fun dbName() : String = "database.db"

    @Singleton
    @Provides
    fun usersCache(db: Database): IGithubUsersCache
        = RoomGithubUsersCache(db)

    @Singleton
    @Provides
    fun userRepositoriesCache(db: Database): IGithubRepositoriesCache
        = RoomGithubRepositoriesCache(db)
}