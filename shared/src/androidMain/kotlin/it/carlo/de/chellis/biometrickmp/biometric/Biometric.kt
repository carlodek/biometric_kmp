package it.carlo.de.chellis.biometrickmp.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

class Biometric private constructor() {
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var executor: Executor

    fun authenticateWithBiometrics(title: String, description: String, negativeButtonText: String) {
        val promptInfo = BiometricPrompt.PromptInfo.Builder().apply {
            setTitle(title)
            setDescription(description)
            setNegativeButtonText(negativeButtonText)
            setAllowedAuthenticators(allowedAuthenticators)
        }.build()
        biometricPrompt.authenticate(promptInfo)
    }

    companion object {
        private const val allowedAuthenticators = BiometricManager.Authenticators.BIOMETRIC_WEAK
        fun canAuthenticate(context: Context): Boolean {
            val biometricManager = BiometricManager.from(context)
            return when (biometricManager.canAuthenticate(allowedAuthenticators)) {
                BiometricManager.BIOMETRIC_SUCCESS -> true
                else -> false
            }
        }

        fun initialize(activity: FragmentActivity, onFail: () -> Unit, onSuccess: () -> Unit) = Biometric().apply {
            executor = ContextCompat.getMainExecutor(activity)
            biometricPrompt = BiometricPrompt(activity, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFail.invoke()
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess.invoke()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onFail.invoke()
                }
            })
        }
    }
}