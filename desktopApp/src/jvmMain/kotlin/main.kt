import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import it.carlo.de.chellis.biometrickmp.App
import java.awt.Dimension

fun main(vararg args: String) = application {
    System.setProperty(
        "jna.library.path",
        args[0]
    )//don't know why jvmArgs in desktop gradle not working..
    val windowState = rememberWindowState(size = DpSize(400.dp, 400.dp))
    Window(title = "Biometric Kmp", state = windowState, onCloseRequest = ::exitApplication) {
        window.minimumSize = Dimension(400, 400)
        App(Any())
    }
}