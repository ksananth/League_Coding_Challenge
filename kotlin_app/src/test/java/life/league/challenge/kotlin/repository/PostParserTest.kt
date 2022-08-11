package life.league.challenge.kotlin.repository

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import life.league.challenge.kotlin.model.Post

internal class PostParserTest : ShouldSpec({

    val parser = PostParser()
    val response = """[
                            {
                                "userId": 3,
                                "id": 26,
                                "title": "est et quae odit qui non",
                                "body": "similique esse doloribus nihil ..."
                            }
                     ]""".trim()

    should("map json array to post list") {
        val res = JsonParser.parseString(response).asJsonArray

        val result= parser.parse(res)

        result shouldBe listOf(Post(3,26,"est et quae odit qui non", "similique esse doloribus nihil ..."))
    }
})