package it.carlo.de.chellis.biometrickmp.android

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import it.carlo.de.chellis.biometrickmp.App
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().also {
            it.setKeepOnScreenCondition { viewModel.keepSplashOnScreen }
            animateSplash(it)
        }
        setContent {
            App()
            LaunchedEffect(Unit) {
                delay(1000L)
                viewModel.keepSplashOnScreen = false
            }
        }
    }
    private fun animateSplash(splash: androidx.core.splashscreen.SplashScreen) {
        splash.setOnExitAnimationListener { splashViewProvider ->
            val slideUp = ObjectAnimator.ofFloat(
                splashViewProvider.view,
                View.TRANSLATION_X,
                0f,
                -splashViewProvider.view.width.toFloat()
            )
            slideUp.interpolator = AnticipateInterpolator()
            slideUp.duration = 300L
            slideUp.doOnEnd { splashViewProvider.remove() }
            slideUp.start()
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
