package com.geekbrains.poplibrary.di.repository

import com.geekbrains.poplibrary.di.repository.module.RepositoryModule
import com.geekbrains.poplibrary.mvp.presenter.FollowPresenter
import com.geekbrains.poplibrary.mvp.presenter.UserInfoPresenter
import com.geekbrains.poplibrary.mvp.presenter.UserRepoPresenter

import com.geekbrains.poplibrary.ui.adapter.FollowRVAdapter
import com.geekbrains.poplibrary.ui.fragment.UserInfoFragment

import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)

interface RepositorySubcomponent {
    fun inject(userInfoFragment: UserInfoFragment)
    fun inject(userInfoPresenter: UserInfoPresenter)
    fun inject(userRepoPresenter: UserRepoPresenter)

    fun inject(followPresenter: FollowPresenter)
    fun inject(followRVAdapter: FollowRVAdapter)
}