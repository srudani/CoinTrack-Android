package com.example.cointrack
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        CoinListViewModel(get())
    }
}

val repositoryModule = module {
    factory {
        CoinListRepository(get())
    }
}

val apiModule = module {
    single { CoinTrackService.getInstance() }
}