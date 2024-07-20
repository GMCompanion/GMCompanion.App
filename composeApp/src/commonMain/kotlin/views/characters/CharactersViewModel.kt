package views.characters

import data.CharacterRepository
import data.HttpCharacterRepository
import data.LocalCharacterRepository
import domain.Character
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {
    private  val characterRepository = LocalCharacterRepository()

    private val _uiState: MutableStateFlow<List<Character>> = MutableStateFlow(listOf())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val characters = characterRepository.getAllCharacters()
                _uiState.value = characters
            } catch (e: Exception) {

            }
        }
    }
}