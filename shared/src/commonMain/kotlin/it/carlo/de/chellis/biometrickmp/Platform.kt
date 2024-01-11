package it.carlo.de.chellis.biometrickmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform