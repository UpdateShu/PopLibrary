package com.geekbrains.poplibrary.di

import com.geekbrains.poplibrary.di.modules.ApiModule
import com.geekbrains.poplibrary.di.modules.AppModule
import com.geekbrains.poplibrary.di.modules.CacheModule
import com.geekbrains.poplibrary.di.modules.CiceroneModule
import com.geekbrains.poplibrary.di.modules.ImageModule
import com.geekbrains.poplibrary.di.modules.RepoModule
import com.geekbrains.poplibrary.mvp.presenter.FollowPresenter

import com.geekbrains.poplibrary.mvp.presenter.MainPresenter
import com.geekbrains.poplibrary.mvp.presenter.UserInfoPresenter
import com.geekbrains.poplibrary.mvp.presenter.UserRepoPresenter
import com.geekbrains.poplibrary.mvp.presenter.UsersPresenter

import com.geekbrains.poplibrary.ui.activity.MainActivity
import com.geekbrains.poplibrary.ui.adapter.UsersRVAdapter
import com.geekbrains.poplibrary.ui.fragment.UserInfoFragment

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
        ApiModule::class,
        AppModule::class,
        CacheModule::class,
        CiceroneModule::class,
        ImageModule::class,
        RepoModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
    fun inject(followPresenter: FollowPresenter)
    fun inject(userRepoPresenter: UserRepoPresenter)
    fun inject(userInfoPresenter: UserInfoPresenter)

    fun inject(userInfoFragment: UserInfoFragment)
}