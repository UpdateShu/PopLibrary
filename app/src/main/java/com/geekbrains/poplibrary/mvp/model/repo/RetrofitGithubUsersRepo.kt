package com.geekbrains.poplibrary.mvp.model.repo

import io.reactivex.rxjava3.schedulers.Schedulers
import com.geekbrains.poplibrary.mvp.model.api.IDataSource

class RetrofitGithubUsersRepo(val api: IDataSource) : IGithubUsersRepo {
    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
    override fun getUserRepositories(user: String) = api.getUserRepositories(user).subscribeOn(Schedulers.io())
}