package com.example.mynetdiarytest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

abstract class BaseListItemDelegate<T : ListItem>(
    @LayoutRes private val layoutId: Int
) : AbsListItemAdapterDelegate<T, ListItem, ListItemViewHolder>() {

    open val viewType: Int = layoutId

    override fun onCreateViewHolder(parent: ViewGroup): ListItemViewHolder =
        ListItemViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false))
}
