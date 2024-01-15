package it.carlo.de.chellis.biometrickmp.flow

import androidx.compose.runtime.MutableState
import it.carlo.de.chellis.biometrickmp.dialog.Dialog
import it.carlo.de.chellis.biometrickmp.utils.closeApp

sealed class Flow {
    data object Splash : Flow()
    data object AuthenticateScreen : Flow()
    data object ResultScreen : Flow()
}

fun MutableState<Flow>.popBack(dialog: MutableState<Dialog?>? = null) {
    when (this.value) {
        Flow.Splash -> {}
        Flow.AuthenticateScreen -> dialog?.value = exitDialog(dialog)
        Flow.ResultScreen -> this.value = Flow.AuthenticateScreen
    }
}

fun exitDialog(dialog: MutableState<Dialog?>? = null) = Dialog(
    "Warning",
    "Would you like to exit from app?",
    Dialog.DialogButton(
        "Yes"
    ) {
        closeApp()
    },
    Dialog.DialogButton(
        "No"
    ) {
        dialog?.value = null
    }
)
