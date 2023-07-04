package com.geekbrains.poplibrary.di.modules

import com.geekbrains.poplibrary.App
import dagger.Module
import dagger.Provides

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Named("uiScheduler")
    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    /*@Named("ioThread")
    @Provides
    fun ioThreadScheduler(): Scheduler = Schedulers.io()*/
}