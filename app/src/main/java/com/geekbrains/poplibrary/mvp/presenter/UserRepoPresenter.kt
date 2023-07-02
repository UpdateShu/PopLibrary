package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.view.UserRepoView
import com.github.terrakok.cicerone.Router

import moxy.MvpPresenter

class UserRepoPresenter(private val router: Router)
    : MvpPresenter<UserRepoView>() {

    var repo: GithubRepository? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        updateRepoInfo()
    }

    private fun updateRepoInfo() {
        repo?.let { repo ->
            repo.name?.let { viewState.setUserRepoName(repo.name) }
            repo.createdAt?.let { viewState.setCreatedAt(repo.createdAt) }
            repo.forksCount?.let { viewState.setForksCount(repo.forksCount) }
        }
    }

    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}