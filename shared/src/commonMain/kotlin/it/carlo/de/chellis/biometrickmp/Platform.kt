package it.carlo.de.chellis.biometrickmp

interface Platform {
    val name: String
    val kindOfPlatform: KindOfPlatform
}

enum class KindOfPlatform {
    Android,
    Ios,
    Desktop
}

expect fun getPlatform(): Platform