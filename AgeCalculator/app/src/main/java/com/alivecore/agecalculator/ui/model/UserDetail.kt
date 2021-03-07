package com.alivecore.agecalculator.ui.model

import java.util.*

class UserDetail {
    lateinit var firstName: String
    lateinit var lastName: String
    var dob: Calendar? = null

    fun getYear(): Int {
        dob?.let {
            val today = Calendar.getInstance()
            var year = today.get(Calendar.YEAR) - it.get(Calendar.YEAR)
            if (today.get(Calendar.MONTH) < it.get(Calendar.MONTH)) {
                year--
            }
            return year
        }
        return 0
    }

    fun getMonth(): Int {
        dob?.let {
            val today = Calendar.getInstance()
            var month = today.get(Calendar.MONTH) - it.get(Calendar.MONTH)
            if (month < 0) {
                month += 12;
            }
            if (month != 0 && today.get(Calendar.DAY_OF_MONTH) < it.get(Calendar.DAY_OF_MONTH)) {
                month--
            }
            return month
        }
        return 0
    }

    fun getDays(): Int {
        dob?.let {
            val today = Calendar.getInstance()
            var days = today.get(Calendar.DAY_OF_MONTH) - it.get(Calendar.DAY_OF_MONTH)
            if (today.get(Calendar.DAY_OF_MONTH) < it.get(Calendar.DAY_OF_MONTH)) {
                val maxDay: Int = it.getActualMaximum(Calendar.DAY_OF_MONTH)
                days = maxDay - it.get(Calendar.DAY_OF_MONTH)
                days += today.get(Calendar.DAY_OF_MONTH)
            }
            return days
        }
        return 0
    }
}