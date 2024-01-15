package it.carlo.de.chellis.biometrickmp.utils

import platform.posix.exit

actual fun closeApp() {
    exit(0)
}