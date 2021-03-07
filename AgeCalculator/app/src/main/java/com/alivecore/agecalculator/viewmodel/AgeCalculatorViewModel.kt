package com.alivecore.agecalculator.viewmodel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alivecore.agecalculator.R
import com.alivecore.agecalculator.ui.model.AgeCalculationResult
import com.alivecore.agecalculator.ui.model.UserDetail
import java.util.*

class AgeCalculatorViewModel : ViewModel() {

    val userDetail = MutableLiveData<UserDetail>()
    val ageCalculationResult = MutableLiveData<AgeCalculationResult>()

    init {
        userDetail.value = UserDetail()
    }

    fun calculateAge() {
        userDetail.value?.let {
            var errorId = -1
            if(TextUtils.isEmpty(it.firstName)){
                errorId = R.string.error_firstName
            } else  if(TextUtils.isEmpty(it.lastName)){
                errorId = R.string.error_lastName
            } else if(it.dob == null){
                errorId = R.string.error_dob
            } else {
                it.dob?.let {
                    if(it.timeInMillis >= Calendar.getInstance().timeInMillis){
                        errorId = R.string.error_dob
                    }
                }
            }
            ageCalculationResult.value = AgeCalculationResult(errorId == -1, errorId)
        }
    }

}