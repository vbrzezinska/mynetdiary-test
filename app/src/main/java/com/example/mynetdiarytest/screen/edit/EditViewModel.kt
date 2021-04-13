package com.example.mynetdiarytest.screen.edit

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.core.repository.ClientsRepository
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    private val data = MutableLiveData<Client>()

    private lateinit var client: Client

    fun getClient(): LiveData<Client> {
        return data
    }

    fun setClient(client: Client) {
        this.client = client
        data.value = this.client
    }

    fun setBodyWeight() {
        client = client.copy(
            weight = 1F
        )
        data.value = client
    }

    fun setDateOfBirth(dateOfBirth: Long) {
        client = client.copy(
            dateOfBirth = dateOfBirth
        )
//        data.value = client
    }

    fun setPhoto(uri: Uri) {
        client = client.copy(
            imageUri = uri
        )
        data.value = client
    }

    fun saveChanges() {
        if (client.id != -1) {
            clientsRepository.updateClient(client)
        } else {
            clientsRepository.addClient(client)
        }
    }
}