package ar.edu.unicen.tpe.dll.model

class MovieList(
    val page: Int = 1,
    val results: List<Movie> = emptyList(),
    val totalPages: Int = 1,
    val totalResults: Int = 0
)