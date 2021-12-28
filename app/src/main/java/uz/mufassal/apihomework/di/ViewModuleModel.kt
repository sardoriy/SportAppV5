package uz.mufassal.apihomework.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.mufassal.apihomework.MainViewModel

val viewModule = module {
    viewModel { MainViewModel(get()) }

}