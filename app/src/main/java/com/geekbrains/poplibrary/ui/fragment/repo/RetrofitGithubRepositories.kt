package com.geekbrains.poplibrary.ui.fragment.repo

import com.geekbrains.poplibrary.mvp.model.api.IDataSource
import com.geekbrains.poplibrary.mvp.model.cache.IGithubRepositoriesCache
import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositories(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubRepositoriesCache) : IGithubRepositories {

    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getUserRepositories(url)
                    .flatMap { repositories ->
                        cache.insertRepositoriesToDB(user, repositories).toSingleDefault(repositories)
                    }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
        } else {
            cache.getRepositoriesFromDB(user)
        }
    }.subscribeOn(Schedulers.io())
}