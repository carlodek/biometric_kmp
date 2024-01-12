package it.carlo.de.chellis.biometrickmp.biometric

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.Foundation.NSError
import platform.LocalAuthentication.LAContext
import platform.LocalAuthentication.LAPolicyDeviceOwnerAuthentication
import platform.LocalAuthentication.LAPolicyDeviceOwnerAuthenticationWithBiometrics

actual class BiometricAuthenticator private actual constructor() {
    actual constructor(activity: Any?) : this()

    @OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
    actual fun doAuth(
        title: String,
        description: String,
        denyBtnText: String,
        actionDone: (Boolean) -> Unit
    ) {
        val laContext = LAContext()
        laContext.setLocalizedCancelTitle(denyBtnText)
        laContext.setLocalizedFallbackTitle("Prova fallback")

        val (canEvaluate: Boolean?, error: NSError?) = memScoped {
            val p = alloc<ObjCObjectVar<NSError?>>()
            val canEvaluate: Boolean? = runCatching {
                laContext.canEvaluatePolicy(LAPolicyDeviceOwnerAuthentication, error = p.ptr)
            }.getOrNull()
            canEvaluate to p.value
        }

        if (error != null) throw error.toException()
        if (canEvaluate == null) {
            actionDone.invoke(false)
            return
        }

        return laContext.evaluatePolicy(
            policy = LAPolicyDeviceOwnerAuthentication,
            localizedReason = title,
            reply = mainContinuation { result: Boolean, error: NSError? ->
                println(error.toString())
                actionDone.invoke(result)
            }
        )
    }


    @OptIn(ExperimentalForeignApi::class)
    actual fun isBiometricAvailable(): Boolean {
        val laContext = LAContext()
        return laContext.canEvaluatePolicy(
            LAPolicyDeviceOwnerAuthenticationWithBiometrics,
            error = null
        )
    }
}

internal fun NSError?.toException(): Exception {
    if (this == null) return NullPointerException("NSError is null")

    return Exception(this.description())
}
