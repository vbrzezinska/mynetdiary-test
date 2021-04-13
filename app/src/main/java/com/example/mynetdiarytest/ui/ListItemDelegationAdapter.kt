package com.example.mynetdiarytest.ui

import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

open class ListItemDelegationAdapter(
    vararg delegates: BaseListItemDelegate<*>
) : ListDelegationAdapter<List<ListItem>>(
    AdapterDelegatesManager<List<ListItem>>().apply {
        delegates.forEach { addDelegate(it.viewType, it) }
    }
)
