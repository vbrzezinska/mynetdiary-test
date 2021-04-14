package com.example.mynetdiarytest.screen.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProvider
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.core.data.WeightUnit
import com.example.mynetdiarytest.screen.BaseFragment
import com.example.mynetdiarytest.screen.injectViewModel
import kotlinx.android.synthetic.main.f_body_weight.*
import javax.inject.Inject

class BodyWeightFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<EditViewModel>(viewModelFactory) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f_body_weight, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        updateWeightHintText()

        weightSeekBar.apply {
            max = MAX_WEIGHt
            progress = viewModel.getBodyWeight()

            setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    updateWeightHintText()
                    viewModel.setBodyWeight(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) { }

                override fun onStopTrackingTouch(seekBar: SeekBar?) { }
            })
        }

        weightUnitsRadioGroup.apply {
            when (viewModel.getBodyWeightUnit()) {
                WeightUnit.KG -> {
                    check(R.id.kgRadioButton)
                }
                WeightUnit.LB -> {
                    check(R.id.lbRadioButton)
                }
            }

            setOnCheckedChangeListener { group, checkedId ->
                when (group.indexOfChild(findViewById<RadioButton>(checkedId))) {
                    0 -> {
                        viewModel.setBodyWeightUnit(WeightUnit.KG)
                    }
                    1 -> {
                        viewModel.setBodyWeightUnit(WeightUnit.LB)
                    }
                }
                updateWeightHintText()
            }
        }
    }

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }

    private fun updateWeightHintText() {
        weightHintTextView.apply {
            text = when (viewModel.getBodyWeightUnit()) {
                WeightUnit.KG -> {
                    getString(R.string.weight_hint, viewModel.getBodyWeight(), getString(R.string.kg))
                }
                WeightUnit.LB -> {
                    getString(R.string.weight_hint, viewModel.getBodyWeight(), getString(R.string.lb))
                }
            }
        }
    }

    companion object {

        private const val MAX_WEIGHt = 500

        fun getInstance(): BodyWeightFragment = BodyWeightFragment()
    }
}