package it.carlo.de.chellis.biometrickmp.biometric

import com.sun.jna.Callback
import com.sun.jna.Library
import com.sun.jna.Native

actual class BiometricAuthenticator private actual constructor() : Library {
    actual constructor(activity: Any?) : this()

    private val native by lazy {
        Native.load("JTouchID", JTouchID::class.java)
    }

    actual fun doAuth(
        title: String,
        description: String,
        denyBtnText: String,
        actionDone: (Boolean) -> Unit
    ) {
        native.touchidAuthenticate(title, object : JTouchID.AuthCallback {
            override fun callback(result: Int, laError: Int) {
                actionDone.invoke(result == 1)
            }
        })
    }

    internal interface JTouchID : Library {
        interface AuthCallback : Callback {
            fun callback(result: Int, laError: Int)
        }

        fun touchidSupported(): Int
        fun touchidAuthenticate(message: String?, callback: AuthCallback?)
    }

    actual fun isBiometricAvailable(): Boolean {
        return native.touchidSupported() == 1
    }
}
