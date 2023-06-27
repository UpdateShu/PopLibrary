package com.geekbrains.poplibrary.mvp.model.repo

import io.reactivex.rxjava3.core.Single
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.GithubUserRepo

interface IGithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUserRepositories(user: String): Single<List<GithubUserRepo>>
}