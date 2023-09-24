package com.geekbrains.poplibrary.di.user.module

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.di.user.UserScope

import com.geekbrains.poplibrary.mvp.model.api.IDataSource
import com.geekbrains.poplibrary.mvp.model.cache.IGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.cache.RoomGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus
import com.geekbrains.poplibrary.mvp.model.repo.IGithubUsers
import com.geekbrains.poplibrary.mvp.model.repo.RetrofitGithubUsers

import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun usersCache(db: Database): IGithubUsersCache
            = RoomGithubUsersCache(db)

    @UserScope
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache)
        : IGithubUsers = RetrofitGithubUsers(api, networkStatus, cache)

    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}