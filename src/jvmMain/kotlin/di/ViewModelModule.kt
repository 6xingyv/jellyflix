package di

import org.koin.dsl.module
import presentation.screen.account.AccountInfoViewModel
import presentation.screen.account.AccountViewModel
import presentation.screen.account.LoginViewModel
import presentation.screen.home.HomeViewModel
import presentation.screen.library.LibraryViewModel

val viewModelModule = module {
    single { HomeViewModel() }
    single { AccountViewModel() }
    single { AccountInfoViewModel() }
    single { LoginViewModel() }
    single { LibraryViewModel() }
}