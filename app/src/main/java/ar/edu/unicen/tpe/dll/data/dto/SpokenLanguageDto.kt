package ar.edu.unicen.tpe.dll.data.dto

import com.google.gson.annotations.SerializedName

class SpokenLanguageDto(
    @SerializedName("english_name") val englishName: String?,
    @SerializedName("iso_639_1") val iso639_1: String?,
    @SerializedName("name") val name: String?
)