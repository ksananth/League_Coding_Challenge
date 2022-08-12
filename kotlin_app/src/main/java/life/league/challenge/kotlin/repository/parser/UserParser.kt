package life.league.challenge.kotlin.repository.parser

import com.google.gson.JsonElement
import life.league.challenge.kotlin.domain.User

class UserParser {
    fun parse(result: JsonElement): List<User> {
        return result.asJsonArray.map {
            val userObject = it.asJsonObject
            val id = userObject["id"].asInt
            val avatar = userObject["avatar"].asString
            val username = userObject["username"].asString

            User(id, avatar, username)
        }
    }
}