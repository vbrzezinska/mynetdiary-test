package com.example.mynetdiarytest.screen.list

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynetdiarytest.MyNetDiaryApp
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.screen.BaseActivity
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
    private val viewModel by lazy { injectViewModel<ClientsViewModel>(viewModelFactory) }

    private val clientDelegate = ClientDelegate {
        startActivity(EditActivity.intentInstance(this, it.client))
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

    override fun onResume() {
        super.onResume()
        viewModel.getClients()
    }

    override fun subscribe() {
        viewModel.getClients().observe(this, Observer { clients ->
            bindView(clients)
        })
    }

    override fun getLayout(): Int = R.layout.a_list

    override fun addDependencies() {
        MyNetDiaryApp.appComponent.inject(this)
    }

    private fun bindView(clients: List<Client> = emptyList()) {
        val items = mutableListOf<ListItem>()

        if (clients.isEmpty()) {
            items.add(EmptyItem())
        } else {
            clients.map { client ->
                items.add(ClientItem(client))
            }
        }

        items.add(ActionsItem())

        itemsAdapter.items = items
        itemsAdapter.notifyDataSetChanged()
    }
}