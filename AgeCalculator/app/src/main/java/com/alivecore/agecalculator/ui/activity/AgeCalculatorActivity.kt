package com.alivecore.agecalculator.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alivecore.agecalculator.R
import com.alivecore.agecalculator.ui.fragment.AgeCalculatorFragment

class AgeCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_calculator)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, AgeCalculatorFragment())
        fragmentTransaction.commit()
    }
}
