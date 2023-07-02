package com.geekbrains.poplibrary.mvp.presenter

import android.annotation.SuppressLint
import android.util.Log

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.presenter.list.IUserRepoListPresenter
import com.geekbrains.poplibrary.mvp.view.UserInfoView
import com.geekbrains.poplibrary.mvp.view.list.UserRepoItemView

import com.geekbrains.poplibrary.navigation.IScreens
import com.geekbrains.poplibrary.ui.fragment.repo.RetrofitGithubRepositories

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserInfoPresenter(private val usersRepo: RetrofitGithubRepositories,
                        private val router: Router,
                        private val screens: IScreens,
                        private val uiScheduler: Scheduler)
    : MvpPresenter<UserInfoView>() {

    var user : GithubUser? = null

    class UserReposListPresenter : IUserRepoListPresenter {
        val repos = mutableListOf<GithubRepository>()

        override var itemClickListener: ((UserRepoItemView) -> Unit)? = null

        override fun bindView(view: UserRepoItemView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setRepoName(it) }
        }

        override fun getCount() = repos.size
    }

    val userReposListPresenter = UserReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        updateUserInfo()

        userReposListPresenter.itemClickListener = { userRepoItemView ->
            val repo = userReposListPresenter.repos[userRepoItemView.pos]
            router.navigateTo(screens.userRepo(repo))
        }
    }

    private fun updateUserInfo() {
        user?.let {
            viewState.setUserLogin(it.login)
            it.avatarUrl?.let { avatarUrl ->
                viewState.setUserAvatar(avatarUrl)
            }
            subscribeOnUserRepos(it)
            //subscribeOnUserRepos("https://api.github.com/users/UpdateShu/repos")
        }
    }

    @SuppressLint("CheckResult")
    private fun subscribeOnUserRepos(user: GithubUser) {
        usersRepo.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({repos ->
                userReposListPresenter.repos.clear()
                userReposListPresenter.repos.addAll(repos)
                viewState.updateUserRepoList()
            }, {
                Log.e("GithubUsers", "Error: ${it.message}")
            })
    }

    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}