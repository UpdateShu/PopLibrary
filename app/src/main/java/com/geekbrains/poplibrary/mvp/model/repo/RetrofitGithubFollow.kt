package com.geekbrains.poplibrary.mvp.model.repo

import com.geekbrains.poplibrary.mvp.model.api.IDataSource
import com.geekbrains.poplibrary.mvp.model.cache.IGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.network.INetworkStatus

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubFollow(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubUsersCache): IGithubFollow {

    override fun getFollowers(user: GithubUser): Single<List<GithubUser>> {
        user.followersUrl?.let {
            return api.getFollowers(it).subscribeOn(Schedulers.io())
        }
        return Single.error<List<GithubUser>>(
            RuntimeException("No followers")).subscribeOn(Schedulers.io())
    }

    override fun getFollowings(user: GithubUser): Single<List<GithubUser>> {
        user.followingUrl?.let {
            //оригинальный запрос не работает
            val url = it.replace("following{/other_user}", "following")
            return api.getFollowing(url).subscribeOn(Schedulers.io())
        }
        return Single.error<List<GithubUser>>(
            RuntimeException("No following")).subscribeOn(Schedulers.io())
    }
}