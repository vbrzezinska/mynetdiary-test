package com.example.mynetdiarytest.screen.list.mapper

import android.content.Context
import com.example.mynetdiarytest.R
import com.example.mynetdiarytest.core.data.Client
import com.example.mynetdiarytest.core.data.WeightUnit
import com.example.mynetdiarytest.screen.list.adapter.ClientItem
import java.text.SimpleDateFormat
import java.util.*

class ViewModelMapper(
    private val context: Context
) {
    private val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

    fun toViewModel(
        client: Client
    ) = ClientItem(
        weightText = getWeightText(client),
        dateOfBirthText = dateFormat.format(Date(client.dateOfBirth)),
        client = client
    )

    private fun getWeightText(
        client: Client
    ): String {
        return when (client.weightUnit) {
            WeightUnit.KG -> {
                context.getString(
                    R.string.weight_format,
                    client.weight,
                    context.getString(R.string.kg)
                )
            }
            WeightUnit.LB -> {
                context.getString(
                    R.string.weight_format,
                    client.weight,
                    context.getString(R.string.lb)
                )
            }
        }
    }
}