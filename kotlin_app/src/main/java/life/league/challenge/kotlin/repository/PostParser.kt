package life.league.challenge.kotlin.repository

import com.google.gson.JsonElement
import life.league.challenge.kotlin.model.Post

class PostParser {

    fun parse(result: JsonElement): List<Post> {
        return result.asJsonArray.map {
            val postObject = it.asJsonObject
            val id = postObject["id"].asInt
            val userId = postObject["userId"].asInt
            val title = postObject["title"].asString
            val body = postObject["body"].asString
            Post(userId, id, title, body)
        }
    }

}
