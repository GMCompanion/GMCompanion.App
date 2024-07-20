package views.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
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
import domain.InventoryItem

class InventoryView(val character: Character) : Screen {
    @Composable
    override fun Content() {
        val viewModel: InventoryViewModel = getViewModel("inventoryViewModel", viewModelFactory { InventoryViewModel(character) })
        val items : MutableList<InventoryItem> by viewModel.uiState.collectAsState()
        LazyColumn {
            items(items){ item ->
                ItemCard(item)
            }
        }
    }

    @Composable
    fun ItemCard(item: InventoryItem) {
        val navigator = LocalNavigator.current

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    text = item.name,
                    textAlign = TextAlign.Center
                )
            }
        }

        Divider()
    }
}