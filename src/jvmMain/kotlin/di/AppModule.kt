package di

import data.repository.JellyfinRepository
import data.repository.JellyfinRepositoryImpl
import data.source.remote.JellyfinApi
import org.koin.dsl.module

val appModule = module {
    single { JellyfinApi() }
    single<JellyfinRepository> { JellyfinRepositoryImpl(get())}
}