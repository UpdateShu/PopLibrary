package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.mvp.model.entity.GithubUserRepo
import com.geekbrains.poplibrary.mvp.view.UserRepoView
import com.github.terrakok.cicerone.Router

import moxy.MvpPresenter

class UserRepoPresenter(val router: Router)
    : MvpPresenter<UserRepoView>() {

    var repo: GithubUserRepo? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        updateRepoInfo()
    }

    fun updateRepoInfo() {
        repo?.let {
            viewState.setUserRepoName(it.name)
            viewState.setCreatedAt(it.createdAt)
            viewState.setForksCount(it.forksCount)
        }
    }

    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}