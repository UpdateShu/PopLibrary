package com.geekbrains.poplibrary.di.modules

import com.geekbrains.poplibrary.mvp.model.api.IDataSource
import com.geekbrains.poplibrary.mvp.model.cache.IGithubRepositoriesCache
import com.geekbrains.poplibrary.mvp.model.cache.IGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus
import com.geekbrains.poplibrary.mvp.model.repo.IGithubRepositories
import com.geekbrains.poplibrary.mvp.model.repo.IGithubUsers
import com.geekbrains.poplibrary.mvp.model.repo.RetrofitGithubRepositories
import com.geekbrains.poplibrary.mvp.model.repo.RetrofitGithubUsers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUsersCache,
    ): IGithubUsers = RetrofitGithubUsers(api, networkStatus, cache)

    @Singleton
    @Provides
    fun userRepositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubRepositoriesCache,
    ): IGithubRepositories = RetrofitGithubRepositories(api, networkStatus, cache)
}