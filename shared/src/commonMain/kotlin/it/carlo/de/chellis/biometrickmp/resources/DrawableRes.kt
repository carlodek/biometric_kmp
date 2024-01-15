package it.carlo.de.chellis.biometrickmp.resources

sealed class DrawableRes {
    abstract val icoBack: String
    abstract val icoClose:String

    data object LightMode : DrawableRes() {
        override val icoBack: String get() = "arrow_back_light.xml"
        override val icoClose: String get()= "close_light.xml"
    }

    data object NightMode : DrawableRes() {
        override val icoBack: String get() = "arrow_back_night.xml"
        override val icoClose: String get()= "close_night.xml"
    }
}