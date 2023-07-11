package com.geekbrains.poplibrary.di.repository.module

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.di.repository.RepositoryScope

import com.geekbrains.poplibrary.mvp.model.api.IDataSource
import com.geekbrains.poplibrary.mvp.model.cache.IGithubRepositoriesCache
import com.geekbrains.poplibrary.mvp.model.cache.IGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.cache.RoomGithubRepositoriesCache
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus

import com.geekbrains.poplibrary.mvp.model.repo.IGithubFollow
import com.geekbrains.poplibrary.mvp.model.repo.IGithubRepositories
import com.geekbrains.poplibrary.mvp.model.repo.RetrofitGithubFollow
import com.geekbrains.poplibrary.mvp.model.repo.RetrofitGithubRepositories

import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun userRepositoriesCache(db: Database): IGithubRepositoriesCache
            = RoomGithubRepositoriesCache(db)

    @RepositoryScope
    @Provides
    fun userRepositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache,
    ): IGithubRepositories = RetrofitGithubRepositories(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    fun userFollowRepo(api: IDataSource,
                   networkStatus: INetworkStatus,
                   cache: IGithubUsersCache
    )
            : IGithubFollow = RetrofitGithubFollow(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}