package it.carlo.de.chellis.biometrickmp.biometric

import androidx.fragment.app.FragmentActivity

actual class BiometricAuthenticator private actual constructor() {
    lateinit var applicationContext: FragmentActivity

    actual constructor(activity: Any?) : this() {
        applicationContext = activity as FragmentActivity
    }

    actual fun doAuth(
        title: String,
        description: String,
        denyBtnText: String,
        actionDone: (Boolean) -> Unit
    ) {
        Biometric.initialize(applicationContext, onFail = {
            actionDone.invoke(false)
        }, onSuccess = {
            actionDone.invoke(true)
        }).authenticateWithBiometrics(title, description, denyBtnText)
    }

    actual fun isBiometricAvailable(): Boolean = Biometric.canAuthenticate(applicationContext)
}