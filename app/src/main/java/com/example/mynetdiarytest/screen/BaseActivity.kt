package com.example.mynetdiarytest.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        addDependencies()
        super.onCreate(savedInstanceState)

        setContentView(getLayout())

        subscribe()
    }

    abstract fun getLayout(): Int

    abstract fun addDependencies()

    abstract fun subscribe()
}

inline fun <reified T : ViewModel> AppCompatActivity.injectViewModel(factory: ViewModelProvider.Factory): T {
    return ViewModelProviders.of(this, factory)[T::class.java]
}