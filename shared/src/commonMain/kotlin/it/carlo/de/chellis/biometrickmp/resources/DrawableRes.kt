package it.carlo.de.chellis.biometrickmp.resources

sealed class DrawableRes {
    abstract val icoBack: String

    data object LightMode : DrawableRes() {
        override val icoBack: String get() = "arrow_back_light.xml"
    }

    data object NightMode : DrawableRes() {
        override val icoBack: String get() = "arrow_back_night.xml"
    }
}