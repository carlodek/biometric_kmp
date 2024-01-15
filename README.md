# biometric_kmp

Kmp project to handle biometric authentication in all platforms

## Description

Project is written using kotlin-multi-platform sharing code and ui thanks to compose by jetbrains. to get started with kmp
click [here](https://kotlinlang.org/docs/multiplatform-get-started.html).</br>
The aim of this project is to show how you can handle different biometric authentication using expect class and actual class thanks to kotlin multi platform
environment.</br>
Composables previews are into androidApp module into the package previews.</br>
Biometric authentication is handled in:

- android: androidx.biometric;
- ios: platform.LocalAuthentication;
- macOS: dylib written in Objective-c and passed to jvmArgs;

## Implementation:

### Common

``` kotlin
expect class BiometricAuthenticator private constructor() {
    constructor(activity: Any?)

    fun doAuth(
        title: String,
        description: String,
        denyBtnText: String,
        actionDone: (Boolean) -> Unit
    )

    /**
     * Performs a biometric scan availability check
     *
     * @return true if it is possible to use a biometry, false - if it is not available
     */
    fun isBiometricAvailable(): Boolean
}
```

### Android (module androidMain)

``` kotlin
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
```

### Ios(module iosMain)

``` kotlin
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
```

### Desktop(module desktopMain, P.N.: currently works on mac)

``` kotlin
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
```

## Example usage

``` kotlin
    val authenticator = BiometricAuthenticator(context)
    val isAuthenticatorAvailable = authenticator.isBiometricAvailable()
    authenticator.doAuth(
        title = "this is the title",
        description = "this is the description",
        denyBtnText = "no, i dont' want to"
    ) {it:Boolean-> 
        
    }
```