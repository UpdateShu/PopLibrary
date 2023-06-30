package com.geekbrains.poplibrary.mvp.model.api

import io.reactivex.rxjava3.core.Single
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository

import retrofit2.http.Url
import retrofit2.http.GET

interface IDataSource {
    @GET("/users")
    fun getUsers() : Single<List<GithubUser>>

    @GET
    fun getUserRepositories(@Url reposUrl: String)
        : Single<List<GithubRepository>>
}