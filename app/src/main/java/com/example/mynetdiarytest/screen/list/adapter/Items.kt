package com.example.mynetdiarytest.screen.list.adapter

import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.ui.ListItem

data class ClientItem(
    val weightText: String,
    val dateOfBirthText: String,
    val client: Client
) : ListItem

class EmptyItem : ListItem

class ActionsItem : ListItem