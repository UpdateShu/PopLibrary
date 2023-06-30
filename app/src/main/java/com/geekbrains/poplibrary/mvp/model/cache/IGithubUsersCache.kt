package com.geekbrains.poplibrary.mvp.model.cache

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun insertUsersToDB(users: List<GithubUser>) : Completable
    fun getUsersFromDB() : Single<List<GithubUser>>
}