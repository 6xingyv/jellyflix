package di

import org.koin.dsl.module
import presentation.screen.home.HomeViewModel
import presentation.screen.login.LoginViewModel

val viewModelModule = module {
    single { HomeViewModel() }
    single { LoginViewModel() }
}