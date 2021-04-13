package com.example.mynetdiarytest.screen.list

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.screen.BaseActivity
import com.example.mynetdiarytest.screen.MyNetDiaryViewModel
import com.example.mynetdiarytest.screen.edit.EditActivity
import com.example.mynetdiarytest.screen.injectViewModel
import com.example.mynetdiarytest.screen.list.adapter.*
import com.example.mynetdiarytest.ui.ListItem
import com.example.mynetdiarytest.ui.ListItemDelegationAdapter
import kotlinx.android.synthetic.main.a_list.*
import javax.inject.Inject

class ClientsListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy { injectViewModel<MyNetDiaryViewModel>(viewModelFactory) }

    private val clientDelegate = ClientDelegate {
        startActivity(EditActivity.intentInstance(this))
    }
    private val emptyDelegate = EmptyDelegate()
    private val actionsDelegate = ActionsDelegate {
        startActivity(EditActivity.intentInstance(this))
    }

    private val itemsAdapter by lazy {
        ListItemDelegationAdapter(
            clientDelegate,
            emptyDelegate,
            actionsDelegate
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        clientsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }

        bindView()
    }

    override fun subscribe() {
        // TODO implement
    }

    override fun getLayout(): Int = R.layout.a_list

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }

    private fun bindView() {
        itemsAdapter.apply {
            items = mutableListOf<ListItem>().apply {
                add(EmptyItem())
                add(ActionsItem())
            }

            notifyDataSetChanged()
        }
    }
}