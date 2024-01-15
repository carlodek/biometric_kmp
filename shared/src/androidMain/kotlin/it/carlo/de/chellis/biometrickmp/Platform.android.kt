package it.carlo.de.chellis.biometrickmp

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    override val kindOfPlatform: KindOfPlatform = KindOfPlatform.Android
}

actual fun getPlatform(): Platform = AndroidPlatform()