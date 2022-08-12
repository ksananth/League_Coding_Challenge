package life.league.challenge.kotlin.repository.parser

import com.google.gson.JsonElement
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.repository.APIInvalidException

class PostParser {

    fun parse(result: JsonElement): List<Post> {
        if (result.isJsonObject && result.asJsonObject.has("message")) {
            throw APIInvalidException(result.asJsonObject.get("message").asString)
        }
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
