package data

import domain.Item
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ItemRepository {
    suspend fun  getItem(itemId : Int) : Item
    suspend fun  getAllItems() : List<Item>
}

class HttpItemRepository : ItemRepository{
    private val client = HttpClient(){
        install(ContentNegotiation){
            json(json = Json {ignoreUnknownKeys = true}, contentType = ContentType.Any)
        }
    }

    override suspend fun getItem(itemId: Int): Item {
        TODO("Not yet implemented")
    }

    override suspend fun getAllItems(): List<Item> {
        TODO("Not yet implemented")
    }

}