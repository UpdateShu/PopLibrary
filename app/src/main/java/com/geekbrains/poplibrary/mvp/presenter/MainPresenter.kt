package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.mvp.view.MainViewImpl
import com.geekbrains.poplibrary.navigation.IScreens

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainViewImpl>() {

    @Inject lateinit var router: Router

    @Inject lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}