package com.geekbrains.poplibrary.ui.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.poplibrary.mvp.presenter.MainPresenter
import com.geekbrains.poplibrary.databinding.ActivityMainBinding
import com.geekbrains.poplibrary.mvp.model.GithubUsersRepo
import com.geekbrains.poplibrary.mvp.view.MainViewImpl
import com.geekbrains.poplibrary.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainViewImpl {

    private var vb: ActivityMainBinding? = null

    private val presenter by moxyPresenter {
        MainPresenter(GithubUsersRepo())
    }

    private var adapter: UsersRVAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun init() {
        vb?.rvUsers?.layoutManager = LinearLayoutManager(this)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}