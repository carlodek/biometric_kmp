package it.carlo.de.chellis.biometrickmp.flow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.carlo.de.chellis.biometrickmp.Greeting
import it.carlo.de.chellis.biometrickmp.biometric.BiometricAuthenticator
import it.carlo.de.chellis.biometrickmp.dialog.Dialog
import it.carlo.de.chellis.biometrickmp.header.Header
import it.carlo.de.chellis.biometrickmp.theme.AndroidActivity
import it.carlo.de.chellis.biometrickmp.theme.ResourcesRefs
import it.carlo.de.chellis.biometrickmp.ui_kit.CenteredComposable
import it.carlo.de.chellis.biometrickmp.ui_kit.HeaderComposable

@Composable
fun AuthenticateScreen(
    flow: MutableState<Flow>,
    dialog: MutableState<Dialog?>
) {
    val resources = ResourcesRefs.current
    HeaderComposable(
        header = Header(
            title = Greeting().greet(),
            imageDx = Header.HeaderImage(
                imgRef = resources.icoClose,
                contentDescription = "exit"
            ) {
                dialog.value = exitDialog(dialog)
            }
        )
    ) {
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