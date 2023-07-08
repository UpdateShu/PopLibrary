package com.geekbrains.poplibrary.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserInfoView : MvpView {
    fun init()
    fun setUserLogin(login: String)
    fun setUserAvatar(avatarUrl: String)
    fun setUserFollowers(followerUsersCount: Int)
    fun setUserFollowings(followingUsersCount: Int)
    fun updateUserRepoList()
}