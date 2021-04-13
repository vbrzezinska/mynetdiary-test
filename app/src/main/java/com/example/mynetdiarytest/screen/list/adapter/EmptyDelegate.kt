package com.example.mynetdiarytest.screen.list.adapter

import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.ui.BaseListItemDelegate
import com.example.mynetdiarytest.ui.ListItem
import com.example.mynetdiarytest.ui.ListItemViewHolder

class EmptyDelegate : BaseListItemDelegate<EmptyItem>(R.layout.i_empty) {

    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean =
        item is EmptyItem

    override fun onBindViewHolder(
        item: EmptyItem,
        holder: ListItemViewHolder,
        payloads: MutableList<Any>
    ) { }
}