package life.league.challenge.kotlin

import life.league.challenge.kotlin.api.Service
import life.league.challenge.kotlin.feature.login.LoginViewModel
import life.league.challenge.kotlin.repository.AuthorizationHelper
import life.league.challenge.kotlin.repository.LoginRepository
import life.league.challenge.kotlin.repository.LoginRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { AuthorizationHelper() }
    single<LoginRepository> { LoginRepositoryImpl(Service.api, get()) }

    viewModel { LoginViewModel(get()) }
}