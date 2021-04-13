package com.example.mynetdiarytest.screen.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.screen.BaseFragment
import com.example.mynetdiarytest.screen.injectViewModel
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

    override fun subscribe() {
        // TODO implement
    }

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }

    companion object {
        fun getInstance(): BodyWeightFragment = BodyWeightFragment()
    }
}