package it.carlo.de.chellis.biometrickmp.android

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import it.carlo.de.chellis.biometrickmp.App
import it.carlo.de.chellis.biometrickmp.flow.Flow
import it.carlo.de.chellis.biometrickmp.flow.popBackStack
import kotlinx.coroutines.delay

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().also {
            it.setKeepOnScreenCondition { viewModel.keepSplashOnScreen }
            animateSplash(it)
        }
        var flow: MutableState<Flow>? = null
        setContent {
            flow = App(this)
            LaunchedEffect(Unit) {
                delay(1000L)
                viewModel.keepSplashOnScreen = false
            }
        }

        this.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                flow?.popBackStack()
            }
        })
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
