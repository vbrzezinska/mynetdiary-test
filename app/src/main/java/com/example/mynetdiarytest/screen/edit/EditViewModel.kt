package com.example.mynetdiarytest.screen.edit

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.core.data.WeightUnit
import com.example.mynetdiarytest.core.repository.ClientsRepository
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val clientsRepository: ClientsRepository
) : ViewModel() {

    private var client: Client = Client.EMPTY

    fun setClient(client: Client) {
        this.client = client
    }

    fun getBodyWeight() = client.weight
    fun setBodyWeight(weight: Int) {
        client = client.copy(
            weight = weight
        )
    }

    fun getBodyWeightUnit() = client.weightUnit
    fun setBodyWeightUnit(weightUnit: WeightUnit) {
        client = client.copy(
            weightUnit = weightUnit
        )
    }

    fun getDateOfBirth() = client.dateOfBirth
    fun setDateOfBirth(dateOfBirth: Long) {
        client = client.copy(
            dateOfBirth = dateOfBirth
        )
    }

    fun getPhoto() = client.imageUri
    fun setPhoto(uri: Uri) {
        client = client.copy(
            imageUri = uri
        )
    }

    fun saveChanges() {
        if (client.id != -1) {
            clientsRepository.updateClient(client)
        } else {
            clientsRepository.addClient(client)
        }
    }
}