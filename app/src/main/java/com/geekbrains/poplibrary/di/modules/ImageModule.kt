package com.geekbrains.poplibrary.di.modules

import android.widget.ImageView

import com.geekbrains.poplibrary.mvp.view.IImageLoader
import com.geekbrains.poplibrary.ui.image.GlideImageLoader

import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun loadInto(): IImageLoader<ImageView> = GlideImageLoader()
}