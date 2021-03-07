package com.alivecore.agecalculator.injector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alivecore.agecalculator.viewmodel.AgeCalculatorViewModel

class AgeViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AgeCalculatorViewModel() as T
    }
}