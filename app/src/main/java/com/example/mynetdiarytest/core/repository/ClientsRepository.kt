package com.example.mynetdiarytest.core.repository

import com.example.mynetdiarytest.core.data.Client

class ClientsRepository {

    private val clients = mutableListOf<Client>()

    private var id: Int = 0

    fun addClient(client: Client) {
        clients.add(client.copy(id = ++id))
    }

    fun updateClient(client: Client) {
        val position = clients.indexOfFirst { it.id == client.id }
        clients[position] = client
    }

    fun getClients() = clients
}