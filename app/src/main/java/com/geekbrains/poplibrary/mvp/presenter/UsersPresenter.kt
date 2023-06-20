package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.mvp.model.GithubUser
import com.geekbrains.poplibrary.mvp.model.GithubUsersRepo
import com.geekbrains.poplibrary.mvp.presenter.list.IUserListPresenter
import com.geekbrains.poplibrary.mvp.view.UsersView
import com.geekbrains.poplibrary.mvp.view.list.UserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    val usersRepo: GithubUsersRepo,
    val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
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
            router.navigateTo(App.instance.screens.userInfo(user))
        }
    }

    fun subscribeOnUsers() {
        usersRepo.create()
            .filter { !it.login.contains('3') }
            .subscribe({ user ->
                val index = usersListPresenter.users.size
                usersListPresenter.users.add(index, user)
                viewState.addUserToList(index)

                println("onNextUser: $" + user.login)
            }, { e ->
                e.message?.let {
                    viewState.showError(it)
                    println("onError: ${it}")
                }
            }, {
                println("onComplete")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}