package com.example.mynetdiarytest.di.module

import android.content.Context
import com.example.mynetdiarytest.screen.list.mapper.ViewModelMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ScreenModule {

    @Singleton
    @Provides
    fun provideClientListViewModelMapper(context: Context) = ViewModelMapper(context)
}