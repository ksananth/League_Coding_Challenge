package life.league.challenge.kotlin.repository.parser

import com.google.gson.JsonParser
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import life.league.challenge.kotlin.model.Post
import life.league.challenge.kotlin.repository.APIInvalidException

internal class PostParserTest : ShouldSpec({

    val parser = PostParser()


    should("map json array to post list") {
        val response = """[
                            {
                                "userId": 3,
                                "id": 26,
                                "title": "est et quae odit qui non",
                                "body": "similique esse doloribus nihil ..."
                            }
                     ]""".trim()
        val res = JsonParser.parseString(response).asJsonArray

        val result= parser.parse(res)

        result shouldBe listOf(Post(3,26,"est et quae odit qui non", "similique esse doloribus nihil ..."))
    }

    should("throw api invalid exception when response contain message") {
        val response = """{"message":"Authorization Failed: API Key invalid"}""".trim()
        val res = JsonParser.parseString(response).asJsonObject


        val result = shouldThrow<APIInvalidException> {
            parser.parse(res)
        }
        result.value shouldBe "Authorization Failed: API Key invalid"
    }
})