package it.carlo.de.chellis.biometrickmp.android.previews

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import it.carlo.de.chellis.biometrickmp.flow.Flow
import it.carlo.de.chellis.biometrickmp.theme.AppTheme

@Composable
fun BasePreview(
    currentFlow: Flow,
    content: @Composable (MutableState<Flow>) -> Unit
) {
    val flow = remember { mutableStateOf(currentFlow) }
    val context = LocalContext.current
    val activity = context.findActivity()
    AppTheme(androidActivity = activity ?: Any()) {
        content(flow)
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}