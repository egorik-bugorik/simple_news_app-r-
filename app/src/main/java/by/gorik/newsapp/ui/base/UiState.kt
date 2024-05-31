package by.gorik.newsapp.ui.base

sealed interface UiState<out T> {

    data class Success<T>(val data:T):UiState<T>
    data class Error(val mesg:String):UiState<Nothing>
    object Loading:UiState<Nothing>


}