package com.example.mynetdiarytest.di

import com.example.mynetdiarytest.di.module.AppModule
import com.example.mynetdiarytest.di.module.ViewModelModule
import com.example.mynetdiarytest.screen.edit.BodyWeightFragment
import com.example.mynetdiarytest.screen.edit.DateOfBirthFragment
import com.example.mynetdiarytest.screen.edit.EditActivity
import com.example.mynetdiarytest.screen.edit.PhotoFragment
import com.example.mynetdiarytest.screen.list.ClientsListActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(view: ClientsListActivity)

    fun inject(view: EditActivity)
    fun inject(view: BodyWeightFragment)
    fun inject(view: DateOfBirthFragment)
    fun inject(view: PhotoFragment)
}