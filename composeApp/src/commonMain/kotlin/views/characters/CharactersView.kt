package views.characters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.Character
import views.inventory.InventoryView

class CharactersView : Screen {
    @Composable
    override fun Content() {
        val viewModel: CharactersViewModel =
            getViewModel(Unit, viewModelFactory { CharactersViewModel() })
        val characters: List<Character> by viewModel.uiState.collectAsState()

        LazyColumn{
            items(characters) { character ->
                CharacterCard(character)
            }
        }
    }

    @Composable
    fun CharacterCard(character: Character) {
        val navigator = LocalNavigator.current

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    navigator?.push(InventoryView(character))
                }
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    text = character.name,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

