package life.league.challenge.kotlin.repository

internal interface PostsRepository {
    suspend fun getPosts()
}

internal class PostsRepositoryImpl : PostsRepository {

    override suspend fun getPosts() {
        TODO("Not yet implemented")
    }
}