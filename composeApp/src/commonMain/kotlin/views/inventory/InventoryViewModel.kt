package views.inventory

import androidx.compose.runtime.mutableStateListOf
import data.HttpCharacterRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.Character
import domain.InventoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InventoryViewModel(val character: Character) : ViewModel() {
    private  val characterRepository = HttpCharacterRepository()

    private val _uiState =  MutableStateFlow<MutableList<InventoryItem>>(mutableStateListOf())
    val uiState : StateFlow<MutableList<InventoryItem>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val inventory = characterRepository.getCharacterItems(character.id)
            _uiState.value = inventory.toMutableList()
        }
    }
}