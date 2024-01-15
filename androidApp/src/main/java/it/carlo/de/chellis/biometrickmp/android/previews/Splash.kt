package it.carlo.de.chellis.biometrickmp.android.previews

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.carlo.de.chellis.biometrickmp.flow.Flow
import it.carlo.de.chellis.biometrickmp.flow.SplashScreen

@Preview
@Composable
fun SplashScreenPreview() {
    BasePreview(Flow.Splash) {
        SplashScreen(it.first)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SplashScreenPreviewNight() {
    BasePreview(Flow.Splash) {
        SplashScreen(it.first)
    }
}