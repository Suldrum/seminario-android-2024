package ar.edu.unicen.tpe.dll.data.mapper

import ar.edu.unicen.tpe.dll.data.dto.MovieListDto
import ar.edu.unicen.tpe.dll.model.MovieList

/**
 * Convierte un objeto de tipo [MovieListDto] a un modelo de dominio [MovieList].
 *
 * Esta función mapea los datos del DTO `MovieListDto` al modelo `MovieList` de la aplicación,
 * asignando valores predeterminados en caso de que alguno de los campos sea nulo.
 *
 * @receiver MovieListDto instancia de lista de películas en formato DTO.
 * @return MovieList instancia de lista de películas en el modelo de dominio.
 */
fun MovieListDto.toModel(): MovieList {
    return MovieList(
        page = page ?: 1,
        results = results?.map { it.toModel() } ?: emptyList(),
        totalPages = totalPages ?: 1,
        totalResults = totalResults ?: 0
    )
}
