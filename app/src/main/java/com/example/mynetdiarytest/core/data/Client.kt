package com.example.mynetdiarytest.core.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Client(
    val id: Int,
    val weight: Int,
    val weightUnit: WeightUnit,
    val dateOfBirth: Long,
    val imageUri: Uri?
) : Parcelable {

    companion object {
        val EMPTY = Client(
            id = -1,
            weight = 0,
            weightUnit = WeightUnit.KG,
            dateOfBirth = System.currentTimeMillis(),
            imageUri = null)
    }
}

enum class WeightUnit {
    KG, LB
}