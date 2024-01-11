package it.carlo.de.chellis.biometrickmp

class DesktopPlatform : Platform {
    override val name: String
        get() = "Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()