package com.geekbrains.poplibrary.mvp.presenter

import android.util.Log
import com.geekbrains.poplibrary.di.user.module.IUserScopeContainer
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.repo.IGithubUsers
import com.geekbrains.poplibrary.mvp.presenter.list.IUserListPresenter
import com.geekbrains.poplibrary.mvp.view.UsersView
import com.geekbrains.poplibrary.mvp.view.list.UserItemView

import com.geekbrains.poplibrary.navigation.IScreens

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject lateinit var usersRepo: IGithubUsers
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject lateinit var uiScheduler: Scheduler

    @Inject lateinit var userScopeContainer: IUserScopeContainer

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
            view.setLogin(user.login)
            user.type?.let {
                view.setType(it)
            }
            view.checkAdmin(user.siteAdmin)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        subscribeOnUsers()

        usersListPresenter.itemClickListener = { userItemView ->
            val user = usersListPresenter.users[userItemView.pos]
            router.navigateTo(screens.userInfo(user))
        }
    }

    private fun subscribeOnUsers() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateUserList()
            }, {
                Log.e("GithubUsers", "Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        userScopeContainer.releaseUserScope()
        super.onDestroy()
    }
}