package it.carlo.de.chellis.biometrickmp.android.previews

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.carlo.de.chellis.biometrickmp.flow.AuthenticateScreen
import it.carlo.de.chellis.biometrickmp.flow.Flow

@Preview
@Composable
fun BiometricScreenPreview() {
    BasePreview(Flow.AuthenticateScreen) {
        AuthenticateScreen(it.first, it.second)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun BiometricScreenPreviewNight() {
    BasePreview(Flow.AuthenticateScreen) {
        AuthenticateScreen(it.first, it.second)
    }
}