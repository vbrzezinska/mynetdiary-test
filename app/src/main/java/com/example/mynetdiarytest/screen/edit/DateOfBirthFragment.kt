package com.example.mynetdiarytest.screen.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.screen.BaseFragment
import com.example.mynetdiarytest.screen.injectViewModel
import kotlinx.android.synthetic.main.f_date_of_birth.*
import java.util.Calendar
import javax.inject.Inject

class DateOfBirthFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<EditViewModel>(viewModelFactory) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f_date_of_birth, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dobDatePicker.setOnDateChangedListener { _, year, month, day ->
            val calendar = Calendar.getInstance().apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DATE, day)
            }
            viewModel.setDateOfBirth(calendar.timeInMillis)
        }
    }

    override fun subscribe() {
        viewModel.getClient().observe(this, Observer {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = it.dateOfBirth
            }
            dobDatePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE))
        })
    }

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }

    companion object {
        fun getInstance(): DateOfBirthFragment = DateOfBirthFragment()
    }
}