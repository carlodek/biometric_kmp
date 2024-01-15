package it.carlo.de.chellis.biometrickmp.header

import androidx.compose.runtime.Stable

@Stable
data class Header(
    val imageSx: HeaderImage? = null,
    val title: String? = null,
    val imageDx: HeaderImage? = null
) {
    @Stable
    data class HeaderImage(
        val imgRef: String,
        val contentDescription: String? = null,
        val action: () -> Unit
    )
}