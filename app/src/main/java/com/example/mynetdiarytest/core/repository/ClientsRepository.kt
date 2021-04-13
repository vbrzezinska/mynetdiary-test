package com.example.mynetdiarytest.core.repository

import com.example.mynetdiarytest.core.data.Client

class ClientsRepository {

    private val clients = mutableListOf<Client>()

    fun addClient(client: Client) {
        clients.add(client)
    }

    fun getClients() = clients
}