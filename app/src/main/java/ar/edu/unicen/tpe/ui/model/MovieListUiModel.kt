package ar.edu.unicen.tpe.ui.model

import ar.edu.unicen.tpe.dll.model.MovieList
import ar.edu.unicen.tpe.ui.model.MovieUiModel.Companion.toUiModel

/**
 * Modelo de datos para representar una lista de películas en la interfaz de usuario.
 *
 * @param results Lista de modelos de películas.
 * @param totalPages Total de páginas disponibles en la lista de resultados.
 * @param totalResults Total de resultados de películas.
 * @param currentPage Página actual de los resultados.
 */
class MovieListUiModel(
    val results: List<MovieUiModel>,
    val totalPages: Int,
    val totalResults: Int,
    val currentPage: Int
) {
    companion object {
        /**
         * Convierte un modelo de lista de películas de la capa de datos a un modelo de lista de películas de la UI.
         *
         * @return Un objeto de tipo [MovieListUiModel] que contiene la información de la lista de películas.
         */
        fun MovieList.toUiModel(): MovieListUiModel {
            return MovieListUiModel(
                results = results.map { it.toUiModel() } ?: emptyList(), // Convierte cada película a su modelo correspondiente
                totalPages = totalPages, // Total de páginas disponibles
                totalResults = totalResults, // Total de resultados
                currentPage = page // Página actual de los resultados
            )
        }
    }
}
