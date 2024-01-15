package it.carlo.de.chellis.biometrickmp

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val kindOfPlatform: KindOfPlatform get() = KindOfPlatform.Ios
}

actual fun getPlatform(): Platform = IOSPlatform()