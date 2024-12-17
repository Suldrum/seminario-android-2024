package ar.edu.unicen.tpe.dll.data.dto

import com.google.gson.annotations.SerializedName

class ProductionCountryDto(
    @SerializedName("iso_3166_1") val iso3166_1: String?,
    @SerializedName("name") val name: String?
)