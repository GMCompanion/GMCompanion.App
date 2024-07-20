package data

import domain.Character
import domain.InventoryItem
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
    suspend fun getCharacterItems(characterId : Int) : List<InventoryItem>
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

    override suspend fun getCharacterItems(characterId: Int): List<InventoryItem> {
        val items : List<InventoryItem> = client.get("https://testcharacterapi.free.beeceptor.com/items").body<List<InventoryItem>>()
        return items
    }
}

class LocalCharacterRepository : CharacterRepository{
    var number = 0;

    override suspend fun getAllCharacters(): List<Character> {
        delay(200)
        return listOf(
            Character(1, "Test1"),
            Character(2, "Test2"),
            Character(3, "Test3")
        )
    }

    override suspend fun getCharacterItems(characterId: Int): List<InventoryItem> {
        delay(200)
        if(number == 0){
            number += 1

            return listOf(
            InventoryItem(1, 1,"TestItem1", 1),
            InventoryItem(2, 2,"TestItem2", 6),
            InventoryItem(3, 3,"TestItem3", 12),
            InventoryItem(4, 4,"TestItem4", 3)

        )}

        return listOf(
            InventoryItem(1, 1,"TestItem1", 1)
        )
    }
}