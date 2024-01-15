package it.carlo.de.chellis.biometrickmp.biometric

import androidx.fragment.app.FragmentActivity

actual class BiometricAuthenticator private actual constructor() {
    private var applicationContext: FragmentActivity? = null

    actual constructor(activity: Any?) : this() {
        applicationContext = activity as? FragmentActivity
    }

    actual fun doAuth(
        title: String,
        description: String,
        denyBtnText: String,
        actionDone: (Boolean) -> Unit
    ) {
        applicationContext?.let {
            Biometric.initialize(it, onFail = {
                actionDone.invoke(false)
            }, onSuccess = {
                actionDone.invoke(true)
            }).authenticateWithBiometrics(title, description, denyBtnText)
        }
    }

    actual fun isBiometricAvailable(): Boolean {
        if (applicationContext == null) return false
        return Biometric.canAuthenticate(applicationContext!!)
    }
}