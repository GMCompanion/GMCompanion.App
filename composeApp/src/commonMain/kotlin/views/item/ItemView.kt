package views.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.Item

class ItemView(private val itemId : Int) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ItemViewModel = getViewModel("itemViewModel", viewModelFactory { ItemViewModel(itemId) })
        val item : Item by viewModel.uiState.collectAsState()
        Column {
            Text(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                text = item.name,
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth().padding(12.dp),
                text = item.description
            )
        }
    }
}