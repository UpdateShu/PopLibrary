package com.geekbrains.poplibrary.di.modules

import com.geekbrains.poplibrary.App

import dagger.Module
import dagger.Provides

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}