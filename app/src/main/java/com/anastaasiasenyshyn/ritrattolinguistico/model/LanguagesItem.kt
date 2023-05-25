package com.anastaasiasenyshyn.ritrattolinguistico.model


import com.google.gson.annotations.SerializedName

data class LanguagesItem(
    @SerializedName("code")
    val code: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("nativeName")
    val nativeName: String?
)