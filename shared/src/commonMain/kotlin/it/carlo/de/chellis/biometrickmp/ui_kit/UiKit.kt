package it.carlo.de.chellis.biometrickmp.ui_kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalInspectionMode
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun CenteredComposable(modifier: Modifier, content: @Composable () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}

@Composable
fun BaseText(text: String) {
    Text(text = text, color = MaterialTheme.colors.onBackground)
}

/**This is a trick to use jetbrains painterResource and let's build a preview in android module*/
@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppImage(
    resourcePath: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    mockShape: Shape = CircleShape
) {
    if (LocalInspectionMode.current) {
        Box(
            modifier = modifier
                .clip(mockShape)
                .background(Color.Blue)
        )
    } else {
        Image(
            painterResource(resourcePath),
            contentDescription,
            modifier = modifier
        )
    }
}