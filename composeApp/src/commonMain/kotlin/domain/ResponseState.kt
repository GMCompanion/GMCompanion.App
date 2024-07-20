package domain
sealed interface ResponseState {
    data class Success<T>(val data : T)
    data class Error(val exceptionMessage : String) : ResponseState
    data object Loading : ResponseState
    data object Uninitialized : ResponseState
}