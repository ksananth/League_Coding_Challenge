package life.league.challenge.kotlin.api

import com.google.gson.JsonElement
import life.league.challenge.kotlin.model.Account
import life.league.challenge.kotlin.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * Retrofit API interface definition using coroutines. Feel free to change this implementation to
 * suit your chosen architecture pattern and concurrency tools
 */
interface Api {

    @GET("login")
    suspend fun login(@Header("Authorization") credentials: String?): Account

    @GET("posts")
    suspend fun posts(@Query("x-access-token") apiKey: String): JsonElement
}

