package it.carlo.de.chellis.biometrickmp.android.previews

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import it.carlo.de.chellis.biometrickmp.dialog.Dialog
import it.carlo.de.chellis.biometrickmp.flow.Flow
import it.carlo.de.chellis.biometrickmp.theme.AppTheme

@Composable
fun BasePreview(
    currentFlow: Flow,
    content: @Composable (Pair<MutableState<Flow>, MutableState<Dialog?>>) -> Unit
) {
    val flow = remember { mutableStateOf(currentFlow) }
    val dialog = remember { mutableStateOf<Dialog?>(null) }
    val context = LocalContext.current
    val activity = context.findActivity()
    AppTheme(androidActivity = activity ?: Any()) {
        content(flow to dialog)
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}