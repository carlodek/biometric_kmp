package it.carlo.de.chellis.biometrickmp.biometric

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