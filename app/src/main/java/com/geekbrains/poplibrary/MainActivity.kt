package com.geekbrains.poplibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.geekbrains.poplibrary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainViewImpl {

    private var vb: ActivityMainBinding? = null

    private lateinit var presenter: MainPresenter

    private val counterViews = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        vb?.let {
            setContentView(it.root)
            counterViews.addAll(listOf(it.btnCounter1, it.btnCounter2, it.btnCounter3,
                it.btnCounter4, it.btnCounter5))
            presenter = MainPresenter(this, counterViews.size)

            for (counterView in counterViews) {
                counterView.setOnClickListener {
                    presenter.counterViewClick(counterViews.indexOf(counterView))
                }
            }
        }
    }

    override fun setButtonText(index: Int, text: String) {
        counterViews[index].text = text
    }
}