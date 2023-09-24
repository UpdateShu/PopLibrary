package com.geekbrains.poplibrary.ui.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.R
import com.geekbrains.poplibrary.databinding.FragmentUserInfoBinding

import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.presenter.UserInfoPresenter
import com.geekbrains.poplibrary.mvp.view.UserInfoView
import com.geekbrains.poplibrary.mvp.view.IImageLoader

import com.geekbrains.poplibrary.ui.activity.BackButtonListener
import com.geekbrains.poplibrary.ui.adapter.UserReposRVAdapter

import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    private var _binding : FragmentUserInfoBinding ?= null
    private val binding
        get() = _binding!!

    private var adapter: UserReposRVAdapter? = null

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    val presenter: UserInfoPresenter by moxyPresenter {
        UserInfoPresenter().apply {
            App.instance.repositorySubcomponent?.inject(this)
        }
    }

    companion object {
        const val GIT_USER = "user"

        fun newInstance(bundle: Bundle) = UserInfoFragment().apply {
            arguments = bundle

            App.instance.initRepositorySubcomponent()?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            = FragmentUserInfoBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    //@RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*presenter.user = arguments?.getParcelable(
            GIT_USER,
            GithubUser::class.java)*/
        presenter.user = arguments?.getParcelable<GithubUser>(
            GIT_USER)//решение для sdk24
    }

    override fun init() {
        binding.rRepos.layoutManager = LinearLayoutManager(context)
        adapter = UserReposRVAdapter(presenter.userReposListPresenter)
        binding.rRepos.adapter = adapter

        binding.btnFollowers.setOnClickListener {
            presenter.showFollowers(getString(R.string.followers_description))
        }
        binding.btnFollowings.setOnClickListener {
            presenter.showFollowings(getString(R.string.followings_description))
        }
    }

    override fun setUserLogin(login: String) {
        binding.userLogin.text = login
    }

    override fun setUserAvatar(avatarUrl: String) {
        imageLoader.loadInto(avatarUrl, binding.infoAvatar)
    }

    @SuppressLint("SetTextI18n")
    override fun setUserFollowers(followerUsersCount: Int) {
        val description = getString(R.string.followers_description)
        binding.btnFollowers.text = "${description}: $followerUsersCount"
    }

    @SuppressLint("SetTextI18n")
    override fun setUserFollowings(followingUsersCount: Int) {
        val description = getString(R.string.followings_description)
        binding.btnFollowings.text = "${description}: $followingUsersCount"
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUserRepoList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
}