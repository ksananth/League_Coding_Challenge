package life.league.challenge.kotlin.repository

import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.domain.User
import life.league.challenge.kotlin.domain.UserPost
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.repository.parser.PostParser
import retrofit2.HttpException
import java.io.IOException

internal interface PostsRepository {
    suspend fun getPosts(apiKey: String): ApiResponse<List<UserPost>>
}

internal class PostsRepositoryImpl(
    private val api: Api,
    private val parser: PostParser,
    private val userRepository: UserRepository
) : PostsRepository {

    override suspend fun getPosts(apiKey: String): ApiResponse<List<UserPost>> {
        return try {
            val users = (userRepository.getUsers(apiKey) as ApiResponse.Success).data
            val result = api.posts(apiKey)
            val posts = parser.parse(result)
            val userPosts = posts.map { post ->
                val avatar = users.find { it.id == post.userId }?.avatar
                val userName = users.find { it.id == post.userId }?.username
                 UserPost(
                    avatar = avatar,
                    userName = userName,
                    title = post.title,
                    description = post.body
                )
            }
            ApiResponse.Success(data = userPosts)
        } catch (e: HttpException) {
            ApiResponse.ApiError(exception = e)
        } catch (e: IOException) {
            ApiResponse.NoInternetError(exception = e)
        } catch (e: APIInvalidException) {
            ApiResponse.ApiError(exception = e)
        }
    }
}