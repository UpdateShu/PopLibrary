package com.geekbrains.poplibrary.di

import com.geekbrains.poplibrary.di.modules.ApiModule
import com.geekbrains.poplibrary.di.modules.AppModule
import com.geekbrains.poplibrary.di.modules.CiceroneModule
import com.geekbrains.poplibrary.di.modules.DatabaseModule
import com.geekbrains.poplibrary.di.modules.ImageModule
import com.geekbrains.poplibrary.di.user.UserSubcomponent
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
        DatabaseModule::class,
        CiceroneModule::class,
        ImageModule::class
    ]
)

interface AppComponent {
    fun userSubcomponent() : UserSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}