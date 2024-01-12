package it.carlo.de.chellis.biometrickmp.flow

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.carlo.de.chellis.biometrickmp.ui_kit.AppImage
import it.carlo.de.chellis.biometrickmp.ui_kit.CenteredComposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(flow: MutableState<Flow>) {
    val scope = rememberCoroutineScope()
    var visible by rememberSaveable { mutableStateOf(true) }
    AnimatedVisibility(
        visible,
        exit = slideOutHorizontally(animationSpec = tween(durationMillis = 200)) { -it }
    ) {
        CenteredComposable(Modifier.fillMaxSize()) {
            AppImage(
                resourcePath = "compose-multiplatform.xml",
                contentDescription = "Welcome",
                modifier = Modifier.size(200.dp)
            )
        }
    }
    scope.launch {
        delay(800L)
        visible = false
    }
    LaunchedEffect(Unit) {
        delay(1000L)
        flow.value = Flow.AuthenticateScreen
    }
}