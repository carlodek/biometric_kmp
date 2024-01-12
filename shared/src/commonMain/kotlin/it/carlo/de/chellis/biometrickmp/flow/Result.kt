package it.carlo.de.chellis.biometrickmp.flow

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import it.carlo.de.chellis.biometrickmp.theme.ResourcesRefs
import it.carlo.de.chellis.biometrickmp.ui_kit.AppImage
import it.carlo.de.chellis.biometrickmp.ui_kit.BaseText
import it.carlo.de.chellis.biometrickmp.ui_kit.CenteredComposable

@Composable
fun ResultScreen(flow: MutableState<Flow>) {
    val resources = ResourcesRefs.current
    Column(
        Modifier
            .fillMaxSize()
            .semantics { disabled() }
    ) {
        Box(Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                flow.popBackStack()
            }
            .background(MaterialTheme.colors.onBackground)
            .semantics(mergeDescendants = true) { }) {
            AppImage(
                resourcePath = resources.icoBack,
                contentDescription = "back",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(60.dp)
                    .padding(8.dp, 10.dp)
            )
        }
        CenteredComposable(
            Modifier
                .fillMaxWidth()
                .height(0.dp)
                .weight(1f)
        ) {
            BaseText("Authenticated!!")
        }
    }
}