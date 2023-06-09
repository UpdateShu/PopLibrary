package com.geekbrains.poplibrary

class MainPresenter(val view : MainViewImpl, counters: Int ) {

    private val model = CountersModel(counters)

    init {
        for (i in 0..counters.dec()) {
            view.setButtonText(i, model.getCurrent(i).toString())
        }
    }

    fun counterViewClick(index: Int) {
        val nextValue = model.next(index)
        view.setButtonText(index, nextValue.toString())
    }
}