package com.example.mynetdiarytest.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.core.repository.ClientsRepository
import com.example.mynetdiarytest.screen.list.adapter.ClientItem
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class ClientsViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    private val data = MutableLiveData<List<ClientItem>>()

    private val mapper = Mapper()

    fun getClients(): LiveData<List<ClientItem>> {
        data.value = clientsRepository.getClients().map { mapper.toViewModel(it) }
        return data
    }

    class Mapper {
        private val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

        fun toViewModel(
            client: Client
        ) = ClientItem(
            dateOfBirthText = dateFormat.format(Date(client.dateOfBirth)),
            client = client
        )
    }
}