package com.geekbrains.poplibrary.ui.fragment.repo

import com.geekbrains.poplibrary.mvp.model.api.IDataSource
import com.geekbrains.poplibrary.mvp.model.cache.IGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus

import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.core.Single

class RetrofitGithubUsers(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubUsersCache) : IGithubUsers {

    override fun getUsers() : Single<List<GithubUser>>
        = networkStatus.isOnlineSingle().flatMap { isConnection ->
            if (isConnection) {
                api.getUsers()
                    .flatMap {  users ->
                        cache.insertUsersToDB(users).toSingleDefault(users)
                    }
            } else {
                cache.getUsersFromDB()
            }
        }.subscribeOn(Schedulers.io())
}