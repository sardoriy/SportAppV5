package uz.mufassal.apihomework.di

import org.koin.dsl.module
import uz.mufassal.apihomework.repository.Repository

val appModule = module {

    single { Repository(errorConverter = get() ,api = get()) }

}