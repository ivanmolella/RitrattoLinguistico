package com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping


import com.google.gson.annotations.SerializedName

data class ImageMappingItem(
    @SerializedName("color")
    var color: String?,
    @SerializedName("mapping")
    var mapping: List<Mapping?>?
)