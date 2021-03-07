package com.alivecore.agecalculator.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alivecore.agecalculator.R
import com.alivecore.agecalculator.injector.AgeViewModelFactory
import com.alivecore.agecalculator.viewmodel.AgeCalculatorViewModel
import kotlinx.android.synthetic.main.fragment_age_calculator.*
import java.util.*

class AgeCalculatorFragment : Fragment() {

    private lateinit var mViewModel: AgeCalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_age_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(
            activity!!,
            AgeViewModelFactory()
        ).get(AgeCalculatorViewModel::class.java)

        setDatePicker()

        handleAgeCalculationClick()

        observeAgeCalculationResult()
    }

    private fun handleAgeCalculationClick() {
        btn_calculate_age.setOnClickListener {
            mViewModel.userDetail.value?.let {
                it.firstName = ed_firstName.text.toString()
                it.lastName = ed_lastName.text.toString()
                mViewModel.calculateAge()
            }
        }
    }

    private fun observeAgeCalculationResult() {
        mViewModel.ageCalculationResult.observe(this, Observer {
            if (it.success) {
                val fragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.fragment_container, AgeCalculatorPreviewFragment())
                fragmentTransaction.addToBackStack(AgeCalculatorPreviewFragment::class.java.canonicalName)
                fragmentTransaction.commit()
            } else {
                Toast.makeText(context!!, getString(it.error), Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun setDatePicker() {
        dp_dob.setOnClickListener {
            val c = mViewModel.userDetail.value?.dob ?: Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val calendar = Calendar.getInstance()
                    calendar.set(year, monthOfYear, dayOfMonth)
                    mViewModel.userDetail.value?.dob = calendar
                    txt_dob_value.text = String.format(getString(R.string.dob), dayOfMonth, monthOfYear+1, year)
                },
                year,
                month,
                day
            )
            dpd.show()
        }
    }
}