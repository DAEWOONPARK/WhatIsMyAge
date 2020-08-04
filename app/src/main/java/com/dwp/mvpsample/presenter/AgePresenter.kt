package com.dwp.mvpsample.presenter

import com.dwp.mvpsample.`interface`.IPresenterContract
import com.dwp.mvpsample.`interface`.IViewContract
import kotlinx.coroutines.*
import java.util.*

/**
 * Created by dwp on 2020-08-03.
 */
class AgePresenter(private val viewContract: IViewContract): IPresenterContract {

    override fun findAge(birth: String) {
        viewContract.hideAgeView()

        if(birth.isBlank() || birth.length != 8) {
            viewContract.showValidationError()
            return
        }

        viewContract.showProgressBar()

        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)

            val birthYear = Integer.parseInt(birth.substring(0, 4))
            val birthMonth = Integer.parseInt(birth.substring(4, 6))
            val birthDay = Integer.parseInt(birth.substring(6, 8))

            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1
            val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            
            val koAge = currentYear - birthYear + 1 // 한국 나이 계산
            var inAge = currentYear - birthYear       // 만 나이 계산

            if(birthMonth * 100 + birthDay > currentMonth * 100 + currentDay)
                inAge--

            withContext(Dispatchers.Main) {
                viewContract.hideProgressBar()

                if(inAge > 0) {
                    viewContract.showAgeOnScreen(inAge, koAge)
                } else {
                    viewContract.showValidationError()
                }
            }
        }
    }
}