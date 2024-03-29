package com.geekbrains.poplibrary.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.databinding.FragmentUsersBinding
import com.geekbrains.poplibrary.di.user.UserSubcomponent

import com.geekbrains.poplibrary.ui.activity.BackButtonListener
import com.geekbrains.poplibrary.ui.adapter.UsersRVAdapter
import com.geekbrains.poplibrary.ui.showSnackBarNoAction

import com.geekbrains.poplibrary.mvp.presenter.UsersPresenter
import com.geekbrains.poplibrary.mvp.view.UsersView

import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    private var _binding: FragmentUsersBinding? = null
    private val binding
        get() = _binding!!

    var userSubcomponent: UserSubcomponent? = null
    private var adapter: UsersRVAdapter? = null

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            userSubcomponent = App.instance.initUserSubcomponent()
            userSubcomponent?.inject(this)
        }
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
        adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
            userSubcomponent?.inject(this)
        }
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