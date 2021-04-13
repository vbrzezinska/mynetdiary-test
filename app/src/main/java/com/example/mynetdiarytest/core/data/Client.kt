package com.example.mynetdiarytest.core.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Client(
    val weight: Float
) : Parcelable {

    companion object {
        val EMPTY = Client(0F)
    }
}