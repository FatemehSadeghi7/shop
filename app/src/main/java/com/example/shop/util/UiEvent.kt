package com.example.shop.util


sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    data object NavigateBack : UiEvent()
    data class ShowToast(val message: String) : UiEvent()
}
