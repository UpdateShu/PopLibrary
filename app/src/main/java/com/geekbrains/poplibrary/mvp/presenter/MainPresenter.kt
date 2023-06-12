package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.mvp.model.GithubUser
import com.geekbrains.poplibrary.mvp.model.GithubUsersRepo
import com.geekbrains.poplibrary.ui.list.IUserListPresenter
import com.geekbrains.poplibrary.mvp.view.MainViewImpl
import com.geekbrains.poplibrary.mvp.view.list.UserItemView
import moxy.MvpPresenter

class MainPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<MainViewImpl>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)?= null
            //get() = TODO("Not yet implemented")
            //set(value) {}

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

        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            //TODO
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)

        viewState.updateList()
    }
}