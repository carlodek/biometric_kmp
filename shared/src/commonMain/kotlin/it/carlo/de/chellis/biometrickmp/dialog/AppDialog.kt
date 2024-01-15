package it.carlo.de.chellis.biometrickmp.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.carlo.de.chellis.biometrickmp.ui_kit.CenteredComposable

@Composable
fun AppDialog(
    dialog: Dialog?
) {
    if (dialog == null) return
    CenteredComposable(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.5f))
            .clickable { }
            .semantics { disabled() }
    ) {
        CenteredComposable(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .background(
                    MaterialTheme.colors.onBackground,
                    RoundedCornerShape(16.dp)
                )
                .semantics { disabled() }
        ) {
            Text(
                modifier = Modifier.wrapContentSize().padding(top = 16.dp),
                text = dialog.title,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.background,
                fontSize = 18.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                modifier = Modifier.wrapContentSize(),
                text = dialog.description,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.background,
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(8.dp))
            Row(Modifier.semantics { disabled() }) {
                Spacer(Modifier.width(16.dp))
                CenteredComposable(Modifier
                    .wrapContentHeight()
                    .width(0.dp)
                    .weight(1f)
                    .semantics { disabled() }) {
                    Button(
                        modifier = Modifier.padding(bottom = 16.dp),
                        onClick = dialog.firstButton.action
                    ) {
                        Text(text = dialog.firstButton.text)
                    }
                }
                Spacer(Modifier.width(16.dp))
                dialog.secondButton?.let { secondBtn ->
                    CenteredComposable(Modifier
                        .wrapContentHeight()
                        .width(0.dp)
                        .weight(1f)
                        .semantics { disabled() }) {
                        Button(
                            modifier = Modifier.padding(bottom = 16.dp),
                            onClick = secondBtn.action
                        ) {
                            Text(text = secondBtn.text)
                        }
                    }
                    Spacer(Modifier.width(16.dp))
                }
            }

        }
    }
}