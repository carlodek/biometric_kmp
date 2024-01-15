package it.carlo.de.chellis.biometrickmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import it.carlo.de.chellis.biometrickmp.dialog.AppDialog
import it.carlo.de.chellis.biometrickmp.dialog.Dialog
import it.carlo.de.chellis.biometrickmp.flow.AuthenticateScreen
import it.carlo.de.chellis.biometrickmp.flow.Flow
import it.carlo.de.chellis.biometrickmp.flow.ResultScreen
import it.carlo.de.chellis.biometrickmp.flow.SplashScreen
import it.carlo.de.chellis.biometrickmp.theme.AppTheme

@Composable
fun App(androidActivity: Any): Pair<MutableState<Flow>, MutableState<Dialog?>> {
    val dialog = remember { mutableStateOf<Dialog?>(null) }
    val start = if (getPlatform().kindOfPlatform == KindOfPlatform.Android)
        Flow.AuthenticateScreen
    else
        Flow.Splash
    val flow = remember { mutableStateOf(start) }
    AppTheme(androidActivity) {
        when (flow.value) {
            Flow.Splash -> SplashScreen(flow)
            Flow.AuthenticateScreen -> AuthenticateScreen(flow, dialog)
            Flow.ResultScreen -> ResultScreen(flow)
        }
        AnimatedVisibility(
            visible = dialog.value != null,
            modifier = Modifier.fillMaxSize(),
            enter = fadeIn(animationSpec = tween(1000))
        ) {
            AppDialog(dialog.value)
        }
    }
    return flow to dialog
}