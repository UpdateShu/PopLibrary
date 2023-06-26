package com.geekbrains.poplibrary.mvp.presenter.list

import com.geekbrains.poplibrary.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}