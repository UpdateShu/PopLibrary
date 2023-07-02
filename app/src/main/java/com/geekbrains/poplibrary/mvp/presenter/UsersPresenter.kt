package com.geekbrains.poplibrary.mvp.presenter

import android.util.Log
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.presenter.list.IUserListPresenter
import com.geekbrains.poplibrary.mvp.view.UsersView
import com.geekbrains.poplibrary.mvp.view.list.UserItemView

import com.geekbrains.poplibrary.ui.fragment.repo.RetrofitGithubUsers
import com.geekbrains.poplibrary.navigation.IScreens

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UsersPresenter(private val usersRepo: RetrofitGithubUsers,
                     private val router: Router,
                     private val screens: IScreens,
                     private val uiScheduler: Scheduler)
    : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
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
}