package life.league.challenge.kotlin.repository

import life.league.challenge.kotlin.api.Api
import life.league.challenge.kotlin.api.ApiResponse
import life.league.challenge.kotlin.model.Post
import retrofit2.HttpException
import java.io.IOException

internal interface PostsRepository {
    suspend fun getPosts(apiKey: String): ApiResponse<List<Post>>
}

internal class PostsRepositoryImpl(
    private val api: Api,
    private val parser: PostParser,
    private val authorizationHelper: AuthorizationHelper
) : PostsRepository {

    override suspend fun getPosts(apiKey: String): ApiResponse<List<Post>> {
        return try {
            val auth = authorizationHelper.createAuth(apiKey)
            val result = api.posts(auth)
            val posts = parser.parse(result)
            ApiResponse.Success(data = posts)
        } catch (e: HttpException) {
            ApiResponse.ApiError(exception = e)
        } catch (e: IOException) {
            ApiResponse.NoInternetError(exception = e)
        } catch (e: APIInvalidException) {
            ApiResponse.ApiError(exception = e)
        }
    }
}