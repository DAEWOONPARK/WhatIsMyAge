package com.dwp.mvpsample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dwp.mvpsample.R
import com.dwp.mvpsample.`interface`.IViewContract
import com.dwp.mvpsample.presenter.AgePresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IViewContract {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val agePresenter = AgePresenter(this)

        bt_find_age.setOnClickListener {
            agePresenter.findAge(edit_birth.text.toString())
        }
    }

    override fun showAgeOnScreen(inAge: Int, koAge: Int) {
        txt_age_international.visibility = View.VISIBLE
        txt_age_international.text = getString(R.string.in_age_text, inAge)

        txt_age_korean.visibility = View.VISIBLE
        txt_age_korean.text = getString(R.string.ko_age_text, koAge)
    }

    override fun showValidationError() {
        edit_birth.error = getString(R.string.validation_error_message)
    }

    override fun hideAgeView() {
        txt_age_international.visibility = View.GONE
        txt_age_korean.visibility = View.GONE
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }
}