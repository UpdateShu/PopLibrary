package com.geekbrains.poplibrary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.geekbrains.poplibrary.databinding.ItemRepoBinding
import com.geekbrains.poplibrary.mvp.presenter.list.IUserRepoListPresenter
import com.geekbrains.poplibrary.mvp.view.list.UserRepoItemView

class UserReposRVAdapter(val presenter: IUserRepoListPresenter) : RecyclerView.Adapter<UserReposRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        UserRepoItemView {
        override var pos = INVALID_INDEX

        override fun setRepoName(text: String) = with(vb) {
            tvRepositoryName.text = text
        }

        override fun setCreatedAt(text: String) = with(vb) {
            tvCreatedAt.text = text
        }

        override fun setUpdatedAt(text: String) = with(vb) {
            tvUpdatedAt.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int)    =
        presenter.bindView(holder.apply {
            pos = position
        })

}