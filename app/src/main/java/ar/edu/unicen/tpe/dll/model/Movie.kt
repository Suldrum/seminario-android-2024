package ar.edu.unicen.tpe.dll.model

class Movie(
    val id: Int = 0,
    val title: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val runtime: Int = 0,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0,
    val posterPath: String = "",
    val backdropPath: String = "",
    val genres: List<Genre> = emptyList(),
    val productionCompanies: List<ProductionCompany> = emptyList(),
    val productionCountries: List<ProductionCountry> = emptyList(),
    val spokenLanguages: List<SpokenLanguage> = emptyList(),
    val budget: Int = 0,
    val revenue: Int = 0,
    val status: String = "",
    val homepage: String = "",
    val imdbId: String = "",
    val originalTitle: String = "",
    val adult: Boolean = true,
//    val video: Boolean = true,
    val tagline: String = "",
//    val belongsToCollection: String = "",
    val originalLanguage: String = ""
)