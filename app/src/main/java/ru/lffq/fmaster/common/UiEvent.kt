package ru.lffq.fmaster.common

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val to: String): UiEvent()
    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}

