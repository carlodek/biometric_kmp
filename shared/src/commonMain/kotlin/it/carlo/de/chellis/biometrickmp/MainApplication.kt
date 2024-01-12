package it.carlo.de.chellis.biometrickmp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import it.carlo.de.chellis.biometrickmp.flow.AuthenticateScreen
import it.carlo.de.chellis.biometrickmp.flow.Flow
import it.carlo.de.chellis.biometrickmp.flow.ResultScreen
import it.carlo.de.chellis.biometrickmp.flow.SplashScreen
import it.carlo.de.chellis.biometrickmp.theme.AppTheme

@Composable
fun App(androidActivity: Any):MutableState<Flow> {
    val start = if (getPlatform().name.contains("Android", true))
        Flow.AuthenticateScreen
    else
        Flow.Splash
    val flow = remember { mutableStateOf(start) }
    AppTheme(androidActivity) {
        when (flow.value) {
            Flow.Splash -> SplashScreen(flow)
            Flow.AuthenticateScreen -> AuthenticateScreen(flow)
            Flow.ResultScreen -> ResultScreen(flow)
        }
    }
    return flow
}