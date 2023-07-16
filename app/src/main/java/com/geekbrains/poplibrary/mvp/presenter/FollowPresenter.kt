package com.geekbrains.poplibrary.mvp.presenter

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.presenter.list.IFollowListPresenter
import com.geekbrains.poplibrary.mvp.view.FollowView
import com.geekbrains.poplibrary.mvp.view.list.UserItemView

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class FollowPresenter : MvpPresenter<FollowView>() {

    @Inject
    lateinit var router: Router

    var title : String? = ""
    var followList = mutableListOf<GithubUser>()

    class FollowListPresenter : IFollowListPresenter {

        val followUsers = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = followUsers[view.pos]
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
            view.setLogin(user.login)
            user.type?.let {
                view.setType(it)
            }
            view.checkAdmin(user.siteAdmin)
        }

        override fun getCount() = followUsers.size
    }

    val followListPresenter = FollowListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        title?.let { viewState.setTitle(it) }

        updateFollowUserList()
    }

    private fun updateFollowUserList() {
        followListPresenter.followUsers.clear()
        followListPresenter.followUsers.addAll(followList)
        viewState.updateUserList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}