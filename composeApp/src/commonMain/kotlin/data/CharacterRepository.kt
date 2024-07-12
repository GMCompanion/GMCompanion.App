package data

import domain.Character
import domain.Item
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

interface CharacterRepository {
    suspend fun getAllCharacters() : List<Character>
    suspend fun getCharacterItems(characterId : Int) : List<Item>
}

class  HttpCharacterRepository : CharacterRepository{
    private val client = HttpClient(){
        install(ContentNegotiation){
            json(json = Json {ignoreUnknownKeys = true}, contentType = ContentType.Any)
        }
    }

    override suspend fun getAllCharacters(): List<Character> {
        val characters : List<Character> = client.get("https://testcharacterapi.free.beeceptor.com/characters").body<List<Character>>()
        return characters
    }

    override suspend fun getCharacterItems(characterId: Int): List<Item> {
        TODO("Not yet implemented")
    }
}

class LocalCharacterRepository : CharacterRepository{
    override suspend fun getAllCharacters(): List<Character> {
        delay(20000)
        return listOf(
            Character(1, "Test1"),
            Character(2, "Test2"),
            Character(3, "Test3")
        )
    }

    override suspend fun getCharacterItems(characterId: Int): List<Item> {
        TODO("Not yet implemented")
    }

}