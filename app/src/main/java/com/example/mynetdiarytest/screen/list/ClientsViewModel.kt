package com.example.mynetdiarytest.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynetdiarytest.core.repository.ClientsRepository
import com.example.mynetdiarytest.screen.list.adapter.ClientItem
import com.example.mynetdiarytest.screen.list.mapper.ViewModelMapper
import javax.inject.Inject

class ClientsViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository,
    private val mapper: ViewModelMapper
) : ViewModel() {

    private val data = MutableLiveData<List<ClientItem>>()

    fun getClients(): LiveData<List<ClientItem>> {
        data.value = clientsRepository.getClients().map { mapper.toViewModel(it) }
        return data
    }
}