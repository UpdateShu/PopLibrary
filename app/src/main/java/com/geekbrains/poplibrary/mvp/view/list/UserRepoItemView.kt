package com.geekbrains.poplibrary.mvp.view.list

interface UserRepoItemView : IItemView {
    fun setRepoName(text: String)
    fun setCreatedAt(text: String)
    fun setUpdatedAt(text: String)
}