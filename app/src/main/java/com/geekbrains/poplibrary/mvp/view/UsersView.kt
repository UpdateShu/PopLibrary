package com.geekbrains.poplibrary.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface UsersView : MvpView {
    fun init()
    fun addUserToList(position: Int)
    fun updateUserList()
    fun showError(message: String)
}