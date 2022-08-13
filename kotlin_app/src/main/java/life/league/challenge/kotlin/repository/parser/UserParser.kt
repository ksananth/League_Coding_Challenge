package life.league.challenge.kotlin.repository.parser

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import life.league.challenge.kotlin.domain.User

class UserParser {
    fun parse(result: JsonElement): List<User> {
        return result.asJsonArray.map {
            val userObject = it.asJsonObject
            val id = userObject["id"].asInt
            val avatar = userObject.hasObjectOrNull("avatar")
            val username = userObject.hasObjectOrNull("username")

            User(id, avatar, username)
        }
    }

    private fun JsonObject.hasObjectOrNull(key: String) =
        if (this.has(key)) {
            this[key].asString
        } else null
}