package com.example.mynetdiarytest.screen.list.adapter

import com.example.mynetdiarytest.ui.ListItem

data class ClientItem(
    val weightText: String,
    val dobText: String
) : ListItem

class EmptyItem : ListItem

class ActionsItem : ListItem