package it.carlo.de.chellis.biometrickmp.android.previews

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.carlo.de.chellis.biometrickmp.dialog.AppDialog
import it.carlo.de.chellis.biometrickmp.dialog.Dialog
import it.carlo.de.chellis.biometrickmp.flow.Flow

@Composable
private fun AppDialogPrev() {
    AppDialog(
        Dialog(
            "title",
            "this is the description",
            Dialog.DialogButton(
                "btn text",
                action = {
                }), Dialog.DialogButton("btn text",
                action = {
                }
            )
        ))
}

@Preview
@Composable
fun AppDialogPreview() {
    BasePreview(Flow.AuthenticateScreen) {
        AppDialogPrev()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun AppDialogPreviewNight() {
    BasePreview(Flow.AuthenticateScreen) {
        AppDialogPrev()
    }
}

@Composable
private fun AppDialogPrev1Btn() {
    AppDialog(
        Dialog(
            "title", "this is the description",
            Dialog.DialogButton(
                "btn text",
                action = {
                })
        )
    )
}

@Preview
@Composable
fun AppDialogPreview1Btn() {
    BasePreview(Flow.AuthenticateScreen) {
        AppDialogPrev1Btn()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun AppDialogPreviewNight1Btn() {
    BasePreview(Flow.AuthenticateScreen) {
        AppDialogPrev1Btn()
    }
}