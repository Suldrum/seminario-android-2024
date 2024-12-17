package ar.edu.unicen.tpe.dll.data.dto

import com.google.gson.annotations.SerializedName

class GenreDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
)