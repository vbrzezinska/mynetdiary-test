package com.example.mynetdiarytest.screen.list.adapter

import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.ui.BaseListItemDelegate
import com.example.mynetdiarytest.ui.ListItem
import com.example.mynetdiarytest.ui.ListItemViewHolder
import kotlinx.android.synthetic.main.i_actions.view.*

class ActionsDelegate(
    private val onClickListener: () -> Unit
) : BaseListItemDelegate<ActionsItem>(R.layout.i_actions) {

    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean =
        item is ActionsItem

    override fun onBindViewHolder(
        item: ActionsItem,
        holder: ListItemViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.itemView.addClientButton.setOnClickListener { onClickListener.invoke() }
    }
}