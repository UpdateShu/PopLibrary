package com.geekbrains.poplibrary.mvp.model.repo

import com.geekbrains.poplibrary.mvp.model.api.IDataSource
import com.geekbrains.poplibrary.mvp.model.cache.IGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus
import io.reactivex.rxjava3.core.Single

class MockGithubUsers() : IGithubUsers
{
    override fun getUsers(): Single<List<GithubUser>> {
        val users = mutableListOf<GithubUser>()
        for (index in 1..50) {
            val strId = index.toString()
            val user = GithubUser(
                id = "${strId}",
                login = "login${strId}",
                type = "user${strId}",
                false
            )
            users.add(user)
        }
        return Single.just(users)
    }
}