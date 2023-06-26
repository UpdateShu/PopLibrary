package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.mvp.model.GithubUser
import com.geekbrains.poplibrary.mvp.view.UserInfoView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserInfoPresenter(val router: Router) : MvpPresenter<UserInfoView>() {

    var user : GithubUser? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        refreshUserInfo()
    }

    fun refreshUserInfo() {
        user?.let {
            viewState.setUserLogin(it.login)
        }
    }

    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}