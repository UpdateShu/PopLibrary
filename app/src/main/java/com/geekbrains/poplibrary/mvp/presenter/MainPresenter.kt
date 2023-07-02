package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.mvp.view.MainViewImpl
import com.geekbrains.poplibrary.navigation.IScreens

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens)
    : MvpPresenter<MainViewImpl>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}