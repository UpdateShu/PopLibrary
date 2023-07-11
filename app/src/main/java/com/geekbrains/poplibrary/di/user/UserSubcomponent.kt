package com.geekbrains.poplibrary.di.user

import com.geekbrains.poplibrary.di.repository.RepositorySubcomponent
import com.geekbrains.poplibrary.di.user.module.UserModule

import com.geekbrains.poplibrary.mvp.presenter.UsersPresenter
import com.geekbrains.poplibrary.ui.adapter.UsersRVAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}