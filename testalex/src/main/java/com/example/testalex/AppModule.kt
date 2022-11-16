package com.example.testalex

import androidx.lifecycle.viewmodel.compose.viewModel

val appModule = module {
    viewModel { MainViewModel }
    viewModel { ApplicationViewModel(androidApplication()) }
}