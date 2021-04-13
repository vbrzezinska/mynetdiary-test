package com.example.mynetdiarytest.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.screen.BaseFragment
import com.example.mynetdiarytest.screen.MyNetDiaryViewModel
import com.example.mynetdiarytest.screen.edit.EditActivity
import com.example.mynetdiarytest.screen.injectViewModel
import kotlinx.android.synthetic.main.f_list.*
import javax.inject.Inject

class ClientsListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<MyNetDiaryViewModel>(viewModelFactory) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.f_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addClient.setOnClickListener {
            startActivity(EditActivity.intentInstance(requireContext()))
        }
    }

    override fun subscribe() {
        // TODO implement
    }

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }
}