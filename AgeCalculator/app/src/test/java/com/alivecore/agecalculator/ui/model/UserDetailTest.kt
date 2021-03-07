package com.alivecore.agecalculator.ui.model

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.*

class UserDetailTest {

    lateinit var userDetail: UserDetail

    @Before
    fun setUp() {
        userDetail = UserDetail()
    }

    @Test
    fun getYear() {
        val calendar = Calendar.getInstance()
        calendar.set(1990, 2, 10)
        userDetail.dob = calendar
        val year = userDetail.getYear()
        assertEquals(31, year)
    }

    @Test
    fun getMonth() {
        val calendar = Calendar.getInstance()
        calendar.set(1990, 2, 10)
        userDetail.dob = calendar
        val month = userDetail.getMonth()
        assertEquals(0, month)
    }

    @Test
    fun getDays() {
        val calendar = Calendar.getInstance()
        calendar.set(1990, 2, 10)
        userDetail.dob = calendar
        val days = userDetail.getDays()
        assertEquals(28, days)
    }
}