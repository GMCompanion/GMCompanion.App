package domain

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String
)