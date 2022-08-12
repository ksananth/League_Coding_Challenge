package life.league.challenge.kotlin.repository.parser

import com.google.gson.JsonParser
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import life.league.challenge.kotlin.model.User
import org.junit.jupiter.api.Assertions.*

internal class UserParserTest:ShouldSpec({

    val parser = UserParser()

    should("parse user from response"){
        val response = """[
                                {
                                    "id": 1,
                                    "avatar": "https://i.pravatar.cc/150?u=1",
                                    "name": "Leanne Graham",
                                    "username": "Bret",
                                    "email": "Sincere@april.biz",
                                    "address": {
                                        "street": "Kulas Light",
                                        "suite": "Apt. 556",
                                        "city": "Gwenborough",
                                        "zipcode": "92998-3874",
                                        "geo": {
                                            "lat": "-37.3159",
                                            "lng": "81.1496"
                                        }
                                    },
                                    "phone": "1-770-736-8031 x56442",
                                    "website": "hildegard.org",
                                    "company": {
                                        "name": "Romaguera-Crona",
                                        "catchPhrase": "Multi-layered...",
                                        "bs": "harness real-time e-markets"
                                    }
                                }
                            ]""".trim()
        val res = JsonParser.parseString(response).asJsonArray

        val result= parser.parse(res)

        result shouldBe listOf(User(avatar ="https://i.pravatar.cc/150?u=1", username =  "Bret"))
    }
})