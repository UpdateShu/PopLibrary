package com.geekbrains.poplibrary

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MainViewImpl : MvpView {
    fun setButtonText(index: Int, text: String)
}