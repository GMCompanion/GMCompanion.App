package domain

import kotlinx.serialization.Serializable

@Serializable
class Item(
    val id : Int,
    val name : String,
    val description : String
)