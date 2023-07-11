package com.geekbrains.poplibrary.ui.adapter

import com.geekbrains.poplibrary.mvp.presenter.list.IUserListPresenter

class FollowRVAdapter(override val presenter : IUserListPresenter)
    : UsersRVAdapter(presenter) {
}