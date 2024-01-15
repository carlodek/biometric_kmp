package it.carlo.de.chellis.biometrickmp.ui_kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.carlo.de.chellis.biometrickmp.header.Header
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
private fun Header.HeaderImage?.makeOneComposable() {
    val boxModifier = if (this != null)
        Modifier
        .fillMaxHeight()
        .width(60.dp)
        .semantics { disabled() }
        .clickable(onClick = this.action)
    else Modifier
        .fillMaxHeight()
        .width(60.dp)
        .semantics { disabled() }
    Box(modifier = boxModifier) {
        this@makeOneComposable?.let {
            AppImage(
                resourcePath = this@makeOneComposable.imgRef,
                contentDescription = this@makeOneComposable.contentDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun HeaderComposable(header: Header, content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .semantics { disabled() }
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(MaterialTheme.colors.onBackground)
                .semantics { disabled() }
        ) {
            header.imageSx.makeOneComposable()
            header.title?.let {
                CenteredComposable(Modifier.width(0.dp).weight(1f).height(60.dp)) {
                    Text(
                        text = it,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.background
                    )
                }
            }
            header.imageDx.makeOneComposable()
        }
        content(this)
    }
}

@Composable
fun CenteredComposable(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content(this)
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