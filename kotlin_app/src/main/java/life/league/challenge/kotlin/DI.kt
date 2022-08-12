package life.league.challenge.kotlin

import life.league.challenge.kotlin.api.Service
import life.league.challenge.kotlin.feature.login.LoginViewModel
import life.league.challenge.kotlin.feature.posts.ViewPostsViewModel
import life.league.challenge.kotlin.repository.*
import life.league.challenge.kotlin.repository.PostsRepository
import life.league.challenge.kotlin.repository.PostsRepositoryImpl
import life.league.challenge.kotlin.repository.parser.PostParser
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    factory { AuthorizationHelper() }
    factory { PostParser() }

    single<LoginRepository> { LoginRepositoryImpl(Service.api, get()) }
    single<PostsRepository> { PostsRepositoryImpl(Service.api, get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { params -> ViewPostsViewModel(get(), params.get()) }
}