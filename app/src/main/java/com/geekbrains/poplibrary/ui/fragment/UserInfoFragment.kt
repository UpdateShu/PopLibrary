package com.geekbrains.poplibrary.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager

import com.geekbrains.poplibrary.App
import com.geekbrains.poplibrary.databinding.FragmentUserInfoBinding
import com.geekbrains.poplibrary.mvp.model.api.ApiHolder
import com.geekbrains.poplibrary.mvp.model.cache.RoomGithubRepositoriesCache
import com.geekbrains.poplibrary.mvp.model.cache.RoomGithubUsersCache
import com.geekbrains.poplibrary.mvp.model.entity.GithubUser
import com.geekbrains.poplibrary.mvp.model.entity.room.Database
import com.geekbrains.poplibrary.ui.fragment.repo.RetrofitGithubUsers
import com.geekbrains.poplibrary.mvp.presenter.UserInfoPresenter
import com.geekbrains.poplibrary.mvp.view.UserInfoView
import com.geekbrains.poplibrary.ui.activity.BackButtonListener
import com.geekbrains.poplibrary.ui.adapter.UserReposRVAdapter
import com.geekbrains.poplibrary.ui.fragment.repo.RetrofitGithubRepositories
import com.geekbrains.poplibrary.ui.image.GlideImageLoader

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackButtonListener {

    private var _binding : FragmentUserInfoBinding ?= null
    private val binding
        get() = _binding!!

    var adapter: UserReposRVAdapter? = null
    val imageLoader = GlideImageLoader()

    val presenter: UserInfoPresenter by moxyPresenter {
        val repositoriesRepo = RetrofitGithubRepositories(
            ApiHolder.api,
            App.networkStatus,
            RoomGithubRepositoriesCache(Database.getInstance()))

        UserInfoPresenter(
            repositoriesRepo,
            App.instance.router,
            App.instance.screens,
            AndroidSchedulers.mainThread())
    }

    companion object {
        const val GIT_USER = "user"

        fun newInstance(bundle: Bundle) : UserInfoFragment {
            val fragment = UserInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            = FragmentUserInfoBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.user = arguments?.getParcelable(GIT_USER,
            GithubUser::class.java)
    }

    override fun init() {
        binding.rRepos.layoutManager = LinearLayoutManager(context)
        adapter = UserReposRVAdapter(presenter.userReposListPresenter)
        binding.rRepos.adapter = adapter
    }

    override fun setUserLogin(login: String) {
        binding.userLogin.text = login
    }

    override fun setUserAvatar(avatarUrl: String) {
        imageLoader.loadInto(avatarUrl, binding.infoAvatar)
    }

    override fun updateUserRepoList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
}