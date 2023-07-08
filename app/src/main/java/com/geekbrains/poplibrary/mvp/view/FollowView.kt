package com.geekbrains.poplibrary.mvp.view

import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface FollowView : UsersView {
    fun setTitle(title: String)
}