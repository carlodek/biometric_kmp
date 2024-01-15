package it.carlo.de.chellis.biometrickmp.flow

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.carlo.de.chellis.biometrickmp.header.Header
import it.carlo.de.chellis.biometrickmp.theme.ResourcesRefs
import it.carlo.de.chellis.biometrickmp.ui_kit.BaseText
import it.carlo.de.chellis.biometrickmp.ui_kit.CenteredComposable
import it.carlo.de.chellis.biometrickmp.ui_kit.HeaderComposable

@Composable
fun ResultScreen(flow: MutableState<Flow>) {
    val resources = ResourcesRefs.current
    HeaderComposable(
        Header(
            imageSx = Header.HeaderImage(
                resources.icoBack,
                "back",
            ) {
                flow.popBack()
            }
        )
    ) {
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