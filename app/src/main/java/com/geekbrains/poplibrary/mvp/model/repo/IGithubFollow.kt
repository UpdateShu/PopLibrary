package com.geekbrains.poplibrary.mvp.model.repo

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubFollow {
    fun getFollowers(user: GithubUser): Single<List<GithubUser>>
    fun getFollowings(user: GithubUser): Single<List<GithubUser>>
}