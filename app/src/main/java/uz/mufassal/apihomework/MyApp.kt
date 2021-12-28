package uz.mufassal.apihomework

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import uz.mufassal.apihomework.di.appModule
import uz.mufassal.apihomework.di.netWorkModule
import uz.mufassal.apihomework.di.viewModule

class MyApp() : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidLogger()

            androidContext(applicationContext)

            modules(listOf(appModule  , netWorkModule , viewModule))

        }

    }

}