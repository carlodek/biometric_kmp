package it.carlo.de.chellis.biometrickmp.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import it.carlo.de.chellis.biometrickmp.resources.DrawableRes


//Light Colors
val lightColorPalette = lightColors()

//Dark colors
val darkColorPalette = darkColors()
val ResourcesRefs = compositionLocalOf<DrawableRes> { DrawableRes.LightMode }
val AndroidActivity = compositionLocalOf { Any() }

@Composable
fun AppTheme(
    androidActivity: Any,
    content: @Composable () -> Unit,
) {
    val darkMode = isSystemInDarkTheme()
    val (colors, resourcesRef) = if (darkMode)
        darkColorPalette to DrawableRes.NightMode
    else
        lightColorPalette to DrawableRes.LightMode
    MaterialTheme(colors = colors) {
        CompositionLocalProvider(
            AndroidActivity provides androidActivity,
            ResourcesRefs provides resourcesRef
        ) {
            Box(
                Modifier.fillMaxSize().background(MaterialTheme.colors.background)
                    .semantics { disabled() }) {
                content()
            }
        }
    }
}