package com.geekbrains.poplibrary.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.databinding.FragmentFollowBinding

import com.geekbrains.poplibrary.ui.activity.BackButtonListener
import com.geekbrains.poplibrary.ui.adapter.UsersRVAdapter
import com.geekbrains.poplibrary.ui.showSnackBarNoAction

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.presenter.FollowPresenter
import com.geekbrains.poplibrary.mvp.view.FollowView

import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FollowFragment : MvpAppCompatFragment(), FollowView, BackButtonListener {

    private var _binding: FragmentFollowBinding ?= null
    private val binding
        get() = _binding!!

    private var adapter: UsersRVAdapter ?= null

    val presenter: FollowPresenter by moxyPresenter {
        FollowPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        const val FOLLOW_TITLE = "followTitle"
        const val GIT_FOLLOW_ARR = "followUsers"

        fun newInstance(bundle: Bundle) = FollowFragment().apply {
            arguments = bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
        = FragmentFollowBinding.inflate(inflater, container, false).also {
            _binding = it
    }.root

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.title = arguments?.getString(FOLLOW_TITLE)
        presenter.followList = arguments?.getParcelableArrayList(GIT_FOLLOW_ARR,
            GithubUser::class.java)!!
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {
        binding.rFollowUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.followListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        binding.rFollowUsers.adapter = adapter
    }

    override fun setTitle(title: String) {
        binding.tvFollowTitle.text = title
    }

    override fun updateUserList() {
        adapter?.notifyDataSetChanged()
    }

    override fun addUserToList(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun showError(message: String) {
        binding.rFollowUsers.showSnackBarNoAction(message)
    }

    override fun backPressed() = presenter.backPressed()
}