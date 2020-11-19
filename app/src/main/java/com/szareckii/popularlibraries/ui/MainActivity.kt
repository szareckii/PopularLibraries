package com.szareckii.popularlibraries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.mvp.model.Model
import com.szareckii.popularlibraries.mvp.presenter.Presenter
import com.szareckii.popularlibraries.mvp.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    val presenter = Presenter(Model(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_counter1.setOnClickListener {
            presenter.counterOneClick()
        }

        btn_counter2.setOnClickListener {
            presenter.counterTwoClick()
        }

        btn_counter3.setOnClickListener {
            presenter.counterThreeClick()
        }
    }


    override fun setButtonOneText(text: String) {
        btn_counter1.text = text
    }

    override fun setButtonTwoText(text: String) {
        btn_counter2.text = text
    }

    override fun setButtonThreeText(text: String) {
        btn_counter3.text = text
    }
}