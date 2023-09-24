package com.geekbrains.poplibrary.mvp.view.list

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun setType(text: String)
    fun checkAdmin(siteAdmin: Boolean)
    fun loadAvatar(url: String)
}