package com.geekbrains.poplibrary.mvp.model.cache

import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun insertRepositoriesToDB(user: GithubUser, repositories: List<GithubRepository>)
        : Completable
    fun getRepositoriesFromDB(user: GithubUser) : Single<List<GithubRepository>>
}