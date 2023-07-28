package com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping


import com.google.gson.annotations.SerializedName

data class Mapping(
    @SerializedName("item_code")
    var itemCode: String?,
    @SerializedName("item_name")
    var itemItalianName: String?,
    @SerializedName("x-end")
    var xEnd: Int?,
    @SerializedName("x-init")
    var xInit: Int?,
    @SerializedName("y-end")
    var yEnd: Int?,
    @SerializedName("y-init")
    var yInit: Int?
)