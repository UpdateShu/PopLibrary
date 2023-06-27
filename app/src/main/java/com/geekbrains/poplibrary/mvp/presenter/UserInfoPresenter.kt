package com.geekbrains.poplibrary.mvp.presenter

import android.annotation.SuppressLint
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.GithubUserRepo
import com.geekbrains.poplibrary.mvp.model.repo.IGithubUsersRepo
import com.geekbrains.poplibrary.mvp.presenter.list.IUserRepoListPresenter
import com.geekbrains.poplibrary.mvp.view.UserInfoView
import com.geekbrains.poplibrary.mvp.view.list.UserRepoItemView
import com.geekbrains.poplibrary.navigation.IScreens

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserInfoPresenter(val uiScheduler: Scheduler,
                        val usersRepo: IGithubUsersRepo,
                        val router: Router,
                        val screens: IScreens
) : MvpPresenter<UserInfoView>() {

    var user : GithubUser? = null

    class UserReposListPresenter : IUserRepoListPresenter {
        val repos = mutableListOf<GithubUserRepo>()

        override var itemClickListener: ((UserRepoItemView) -> Unit)? = null

        override fun bindView(view: UserRepoItemView) {
            val repo = repos[view.pos]
            view.setRepoName(repo.name)
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

    fun updateUserInfo() {
        user?.let {
            viewState.setUserLogin(it.login)
            it.avatarUrl?.let { avatarUrl ->
                viewState.setUserAvatar(avatarUrl)
            }
            it.reposUrl?.let { reposUrl ->
                //subscribeOnUserRepos(reposUrl)
                subscribeOnUserRepos("https://api.github.com/users/UpdateShu/repos")
            }
        }
    }

    @SuppressLint("CheckResult")
    fun subscribeOnUserRepos(reposUrl: String) {
        usersRepo.getUserRepositories(reposUrl)
            .observeOn(uiScheduler)
            .subscribe({repos ->
                userReposListPresenter.repos.clear()
                userReposListPresenter.repos.addAll(repos)
                viewState.updateUserRepoList()
            })
    }

    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}