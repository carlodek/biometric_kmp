package it.carlo.de.chellis.biometrickmp.flow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import it.carlo.de.chellis.biometrickmp.Greeting
import it.carlo.de.chellis.biometrickmp.biometric.BiometricAuthenticator
import it.carlo.de.chellis.biometrickmp.theme.AndroidActivity
import it.carlo.de.chellis.biometrickmp.ui_kit.CenteredComposable

@Composable
fun AuthenticateScreen(flow: MutableState<Flow>) {
    Column(
        Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = Greeting().greet(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground
        )
        CenteredComposable(Modifier.fillMaxWidth().height(0.dp).weight(1f)) {
            val context = AndroidActivity.current
            val authenticator = BiometricAuthenticator(context)
            val isAuthenticatorAvailable = authenticator.isBiometricAvailable()
            Button(onClick = {
                authenticator.doAuth(
                    title = "this is the title",
                    description = "this is the description",
                    denyBtnText = "no, i dont' want to"
                ) {
                    if (it) {
                        flow.value = Flow.ResultScreen
                    }
                }
            }, enabled = isAuthenticatorAvailable) {
                Text("try authentication")
            }
        }
    }
}