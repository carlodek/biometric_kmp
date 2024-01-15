package it.carlo.de.chellis.biometrickmp

class DesktopPlatform : Platform {
    override val name: String get() = "Desktop"
    override val kindOfPlatform: KindOfPlatform get() = KindOfPlatform.Desktop
}

actual fun getPlatform(): Platform = DesktopPlatform()