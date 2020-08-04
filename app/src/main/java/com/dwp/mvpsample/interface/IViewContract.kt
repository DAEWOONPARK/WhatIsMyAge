package com.dwp.mvpsample.`interface`

/**
 * Created by dwp on 2020-08-03.
 */
interface IViewContract {
    fun showAgeOnScreen(inAge: Int, koAge: Int)

    fun showValidationError()

    fun hideAgeView()

    fun showProgressBar()

    fun hideProgressBar()
}