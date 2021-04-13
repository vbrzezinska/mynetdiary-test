package com.example.mynetdiarytest.screen.edit

import androidx.lifecycle.ViewModel
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.core.repository.ClientsRepository
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    private lateinit var client: Client

    fun setClient(client: Client) {
        this.client = client
    }

    fun setBodyWeight() {
        client = client.copy(
            weight = 1F
        )
    }

    fun setDateOfBirth() {
        // TODO implement
    }

    fun setPhoto() {
        // TODO implement
    }

    fun saveChanges() {
        clientsRepository.addClient(client)
    }
}