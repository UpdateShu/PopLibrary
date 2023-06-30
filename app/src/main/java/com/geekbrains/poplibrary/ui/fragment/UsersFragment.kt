package com.geekbrains.poplibrary.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.databinding.FragmentUsersBinding
import com.geekbrains.poplibrary.mvp.model.api.ApiHolder
import com.geekbrains.poplibrary.mvp.model.cache.RoomGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import com.geekbrains.poplibrary.ui.fragment.repo.RetrofitGithubUsers
import com.geekbrains.poplibrary.mvp.presenter.UsersPresenter
import com.geekbrains.poplibrary.mvp.view.UsersView
import com.geekbrains.poplibrary.ui.activity.BackButtonListener
import com.geekbrains.poplibrary.ui.adapter.UsersRVAdapter
import com.geekbrains.poplibrary.ui.image.GlideImageLoader
import com.geekbrains.poplibrary.ui.showSnackBarNoAction

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    var adapter: UsersRVAdapter? = null

    val presenter: UsersPresenter by moxyPresenter {
        val usersRepo =  RetrofitGithubUsers(
            ApiHolder.api,
            App.networkStatus,
            RoomGithubUsersCache(Database.getInstance()))

        UsersPresenter(usersRepo,
            App.instance.router,
            App.instance.screens,
            AndroidSchedulers.mainThread()
        )
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
    = FragmentUsersBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.rUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUserList() {
        adapter?.notifyDataSetChanged()
    }

    override fun addUserToList(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun showError(message: String) {
        binding.rUsers.showSnackBarNoAction(message)
    }

    override fun backPressed() = presenter.backPressed()
}