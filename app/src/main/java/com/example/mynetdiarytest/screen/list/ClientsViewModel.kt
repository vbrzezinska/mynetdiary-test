package com.example.mynetdiarytest.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.core.repository.ClientsRepository
import javax.inject.Inject

class ClientsViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    private val data = MutableLiveData<List<Client>>()

    fun getClients(): LiveData<List<Client>> {
        data.value = clientsRepository.getClients()
        return data
    }
}