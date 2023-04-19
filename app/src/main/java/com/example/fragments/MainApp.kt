package com.example.fragments

import android.app.Application
import com.example.fragments.modules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.logger.Level

class MainApplication : Application() {



    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}