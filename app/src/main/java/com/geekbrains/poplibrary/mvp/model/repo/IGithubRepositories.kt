package com.geekbrains.poplibrary.mvp.model.repo

import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositories {
    fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}