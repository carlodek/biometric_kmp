package it.carlo.de.chellis.biometrickmp.dialog

import androidx.compose.runtime.Immutable

@Immutable
data class Dialog(
    val title: String,
    val description: String,
    val firstButton: DialogButton,
    val secondButton: DialogButton? = null
) {
    @Immutable
    data class DialogButton(val text: String, val action: () -> Unit)
}