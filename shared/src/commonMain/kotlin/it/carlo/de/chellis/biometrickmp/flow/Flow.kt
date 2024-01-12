package it.carlo.de.chellis.biometrickmp.flow

import androidx.compose.runtime.MutableState

sealed class Flow {
    data object Splash : Flow()
    data object AuthenticateScreen : Flow()
    data object ResultScreen : Flow()
}

fun MutableState<Flow>.popBackStack() {
    when (this.value) {
        is Flow.Splash -> {}
        is Flow.AuthenticateScreen -> {
        }

        is Flow.ResultScreen -> {
            this.value = Flow.AuthenticateScreen
        }
    }
}