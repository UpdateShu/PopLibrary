package com.geekbrains.poplibrary.mvp.view

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}