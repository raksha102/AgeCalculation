package com.alivecore.agecalculator.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alivecore.agecalculator.R
import com.alivecore.agecalculator.injector.AgeViewModelFactory
import com.alivecore.agecalculator.viewmodel.AgeCalculatorViewModel
import kotlinx.android.synthetic.main.fragment_age_calculator_preview.*

class AgeCalculatorPreviewFragment : Fragment() {

    private lateinit var mViewModel: AgeCalculatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_age_calculator_preview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(
            activity!!,
            AgeViewModelFactory()
        ).get(AgeCalculatorViewModel::class.java)

        mViewModel.userDetail.observe(this, Observer {
            it?.let {

                it.firstName?.let {
                    txt_firstName.text = String.format(getString(R.string.firstName_format), it)
                }

                it.lastName?.let {
                    txt_lastName.text = String.format(getString(R.string.lastName_format), it)
                }

                txt_dob.text = String.format(
                    getString(R.string.dob_format), it.getYear()
                    , it.getMonth()
                    , it.getDays()
                )
            }

        })
    }
}