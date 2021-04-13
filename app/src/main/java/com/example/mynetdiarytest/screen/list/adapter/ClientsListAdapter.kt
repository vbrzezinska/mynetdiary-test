package com.example.mynetdiarytest.screen.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.core.data.Client

class ClientsListAdapter(
    private val values: MutableList<Client> = mutableListOf(),
    private val onEditClick: ((Client) -> Unit)
) : RecyclerView.Adapter<ClientsListAdapter.ClientHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientHolder =
        ClientHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.i_client, parent, false)
        )

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ClientHolder, position: Int) =
        holder.bind(values[position], onEditClick)

    fun updateClients(clients: List<Client>) {
        values.clear()
        values.addAll(clients)
        notifyDataSetChanged()
    }

    class ClientHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(
            client: Client,
            onEditClick: ((Client) -> Unit)
        ) {
            with(itemView) {

            }
        }
    }
}