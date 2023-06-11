package com.geekbrains.poplibrary

import moxy.MvpPresenter

class MainPresenter(val model : CountersModel) : MvpPresenter<MainViewImpl>() {

    var counters : Int
        get() = model.counters
        set(value) {
            model.counters = value
            for (i in 0.. value.dec()) {
                viewState.setButtonText(i, model.getCurrent(i).toString())
            }
        }

    fun counterViewClick(index: Int) {
        val nextValue = model.next(index)
        viewState.setButtonText(index, nextValue.toString())
    }
}