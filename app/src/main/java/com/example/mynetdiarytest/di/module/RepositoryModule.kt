package com.example.mynetdiarytest.di.module

import com.example.mynetdiarytest.core.repository.ClientsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideClientRepository() = ClientsRepository()
}