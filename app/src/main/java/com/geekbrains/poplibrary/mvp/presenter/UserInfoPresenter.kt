package com.geekbrains.poplibrary.mvp.presenter

import android.annotation.SuppressLint
import android.util.Log

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.model.repo.IGithubRepositories
import com.geekbrains.poplibrary.mvp.model.repo.IGithubUsers
import com.geekbrains.poplibrary.mvp.presenter.list.IUserRepoListPresenter
import com.geekbrains.poplibrary.mvp.view.UserInfoView
import com.geekbrains.poplibrary.mvp.view.list.UserRepoItemView

import com.geekbrains.poplibrary.navigation.IScreens

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UserInfoPresenter : MvpPresenter<UserInfoView>() {

    @Inject lateinit var usersRepo: IGithubUsers

    @Inject lateinit var userRepositoriesRepo: IGithubRepositories

    @Inject lateinit var router: Router

    @Inject lateinit var screens: IScreens

    @Inject lateinit var uiScheduler: Scheduler

    var user : GithubUser? = null
    private var followerUsers : MutableList<GithubUser> = mutableListOf()
    private var followingUsers : MutableList<GithubUser> = mutableListOf()

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

    fun showFollowers(title: String) {
        router.navigateTo(screens.followUsers(title, ArrayList(followerUsers)))
    }

    fun showFollowings(title: String) {
        router.navigateTo(screens.followUsers(title, ArrayList(followingUsers)))
    }

    private fun updateUserInfo() {
        user?.let {
            viewState.setUserLogin(it.login)
            it.avatarUrl?.let { avatarUrl ->
                viewState.setUserAvatar(avatarUrl)
            }
            subscribeOnUserRepos(it)
            subscribeOnFollowerUsers(it)
            subscribeOnFollowingUsers(it)
            //subscribeOnUserRepos("https://api.github.com/users/UpdateShu/repos")
        }
    }

    @SuppressLint("CheckResult")
    private fun subscribeOnUserRepos(user: GithubUser) {
        userRepositoriesRepo.getRepositories(user)
            .observeOn(uiScheduler)
            .subscribe({repos ->
                userReposListPresenter.repos.clear()
                userReposListPresenter.repos.addAll(repos)
                viewState.updateUserRepoList()
            }, {
                Log.e("GithubUsers", "Error: ${it.message}")
            })
    }

    private fun subscribeOnFollowerUsers(user: GithubUser) {
        usersRepo.getFollowers(user)
            .observeOn(uiScheduler)
            .subscribe({ followers ->
                followerUsers.clear()
                followerUsers.addAll(followers)
                viewState.setUserFollowers(followerUsers.size)
            }, {
                Log.e("GithubUsers", "Error: ${it.message}")
            })
        }


    private fun subscribeOnFollowingUsers(user: GithubUser) {
        usersRepo.getFollowings(user)
            .observeOn(uiScheduler)
            .subscribe({ followings ->
                followingUsers.clear()
                followingUsers.addAll(followings)
                viewState.setUserFollowings(followingUsers.size)
            }, {
                Log.e("GithubUsers", "Error: ${it.message}")
            })
        }


    fun backPressed() : Boolean {
        router.exit()
        return true
    }
}