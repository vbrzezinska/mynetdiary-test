package com.example.mynetdiarytest.screen.list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.ui.BaseListItemDelegate
import com.example.mynetdiarytest.ui.ListItem
import com.example.mynetdiarytest.ui.ListItemViewHolder
import com.example.mynetdiarytest.utils.loadImage
import kotlinx.android.synthetic.main.i_client.view.*

class ClientDelegate(
    private val onClickListener: (ClientItem) -> Unit
) : BaseListItemDelegate<ClientItem>(
    R.layout.i_client
) {

    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean =
        item is ClientItem

    override fun onBindViewHolder(
        item: ClientItem,
        holder: ListItemViewHolder,
        payloads: MutableList<Any>
    ) {
        holder.itemView.apply {
            weightTextView.text = "${item.client.weight}"
            dobTextView.text = "DOB"

            item.client.imageUri?.let {
                photoImageView.loadImage(it)
            }

            editButton.setOnClickListener { onClickListener.invoke(item) }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        (holder as? ListItemViewHolder)?.itemView?.editButton?.setOnClickListener(null)
    }
}