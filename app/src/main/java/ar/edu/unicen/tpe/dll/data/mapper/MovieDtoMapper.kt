package ar.edu.unicen.tpe.dll.data.mapper

import ar.edu.unicen.tpe.dll.data.dto.MovieDto
import ar.edu.unicen.tpe.dll.model.Movie

/**
 * Convierte un objeto de tipo [MovieDto] a un modelo de dominio [Movie].
 *
 * Esta función toma un objeto `MovieDto`, que representa una película en formato de datos de transferencia,
 * y lo convierte en un objeto `Movie`, que es el modelo de dominio de la aplicación. Todos los campos nulos en
 * `MovieDto` se asignan a valores predeterminados apropiados para asegurar que el modelo de dominio `Movie` esté
 * completamente inicializado.
 *
 * @receiver MovieDto instancia de la película en formato DTO.
 * @return Movie instancia de la película en el modelo de dominio de la aplicación.
 */
fun MovieDto.toModel(): Movie {
    return Movie(
        adult = adult ?: true,
        backdropPath = backdropPath ?: "",
 //       belongsToCollection = belongsToCollection ?: "",
        budget = budget ?: 0,
        genres = genres?.map { it.toModel() } ?: emptyList(),
        homepage = homepage ?: "",
        id = id ?: 0,
        imdbId = imdbId ?: "",
        originalLanguage = originalLanguage ?: "",
        originalTitle = originalTitle ?: "",
        overview = overview ?: "",
        posterPath = posterPath ?: "",
        productionCompanies = productionCompanies?.map { it.toModel() } ?: emptyList(),
        productionCountries = productionCountries?.map { it.toModel() } ?: emptyList(),
        releaseDate = releaseDate ?: "",
        revenue = revenue ?: 0,
        runtime = runtime ?: 0,
        spokenLanguages = spokenLanguages?.map { it.toModel() } ?: emptyList(),
        status = status ?: "",
        tagline = tagline ?: "",
        title = title ?: "",
//        video = video ?: true,
        voteAverage = voteAverage ?: 0.0,
        voteCount = voteCount ?: 0
    )
}
