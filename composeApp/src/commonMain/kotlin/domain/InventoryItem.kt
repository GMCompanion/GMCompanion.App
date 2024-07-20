package domain

import kotlinx.serialization.Serializable

@Serializable
data class InventoryItem(
    val id: Int,
    val itemId : Int,
    val name: String,
    val quantity : Int
)