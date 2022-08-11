package life.league.challenge.kotlin.repository

import life.league.challenge.kotlin.api.Api

internal interface PostsRepository {
    suspend fun getPosts(apiKey: String)
}

internal class PostsRepositoryImpl(
    private val api: Api,
    private val authorizationHelper: AuthorizationHelper
) : PostsRepository {

    override suspend fun getPosts(apiKey: String) {
        val auth = authorizationHelper.createAuth(apiKey)
        api.posts(auth)
    }
}