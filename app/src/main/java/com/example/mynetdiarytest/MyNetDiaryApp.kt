package com.example.mynetdiarytest

import android.app.Application
import android.util.Log
import com.example.mynetdiarytest.di.AppComponent
import com.example.mynetdiarytest.di.DaggerAppComponent
import com.example.mynetdiarytest.di.module.AppModule
import timber.log.Timber

class MyNetDiaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        buildComponents()

        if (BuildConfig.DEBUG) {
            Timber.plant(TagTree())
        }
    }

    private fun buildComponents() {
        val appModule = AppModule(this)

        appComponent =
            DaggerAppComponent.builder()
                .appModule(appModule)
                .build()
    }

    private class TagTree : Timber.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            Log.println(priority, "MyNetDiary", message)
        }
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}