package com.geekbrains.poplibrary.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserRepoView : MvpView {
    fun setUserRepoName(name: String)
    fun setCreatedAt(createdAt: String)
    fun setForksCount(forksCount: Int)
}