package com.example.mynetdiarytest.screen.list

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.screen.BaseActivity
import com.example.mynetdiarytest.screen.MyNetDiaryViewModel
import com.example.mynetdiarytest.screen.injectViewModel
import kotlinx.android.synthetic.main.a_list.*
import javax.inject.Inject

class ClientsListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<MyNetDiaryViewModel>(viewModelFactory) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    override fun subscribe() {
        // TODO implement
    }

    override fun getLayout(): Int = R.layout.a_list

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }
}