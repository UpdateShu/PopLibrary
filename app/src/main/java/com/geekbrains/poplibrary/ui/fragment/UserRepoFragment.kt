package com.geekbrains.poplibrary.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.databinding.FragmentUserRepoBinding
import com.geekbrains.poplibrary.mvp.model.entity.GithubRepository
import com.geekbrains.poplibrary.mvp.presenter.UserRepoPresenter
import com.geekbrains.poplibrary.mvp.view.UserRepoView
import com.geekbrains.poplibrary.ui.activity.BackButtonListener

import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserRepoFragment : MvpAppCompatFragment(), UserRepoView, BackButtonListener {

    private var _binding : FragmentUserRepoBinding ?= null
    private val binding
        get() = _binding!!

    val presenter: UserRepoPresenter by moxyPresenter {
        UserRepoPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        const val GIT_USER_REPO = "repo"

        fun newInstance(bundle: Bundle) : UserRepoFragment {
            val fragment = UserRepoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            = FragmentUserRepoBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.repo = arguments?.getParcelable(GIT_USER_REPO,
            GithubRepository::class.java)
    }

    override fun setUserRepoName(name: String) {
        binding.tvRepoName.text = name
    }

    override fun setCreatedAt(createdAt: String) {
        binding.tvRepoCreated.text = createdAt
    }

    override fun setForksCount(forksCount: Int) {
        binding.tvForksCount.text = forksCount.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
}