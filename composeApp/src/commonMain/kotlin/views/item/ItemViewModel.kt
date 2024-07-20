package views.item

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.ResponseState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModel(private val itemId : Int) : ViewModel() {
    private val _uiState =  MutableStateFlow<ResponseState>(ResponseState.Uninitialized)
    val uiState : StateFlow<ResponseState> = _uiState.asStateFlow()

    init{
        _uiState.value = ResponseState.Loading
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = ResponseState.Error(e.message.orEmpty())
            }
        }
    }
}