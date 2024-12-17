package ar.edu.unicen.tpe.dll.data.dto

import com.google.gson.annotations.SerializedName

class MovieListDto (
    @SerializedName("page") val page: Int?,
    @SerializedName("results")  val results: List<MovieDto>?,
    @SerializedName("total_pages")  val totalPages: Int?,
    @SerializedName("total_results")  val totalResults: Int?
)