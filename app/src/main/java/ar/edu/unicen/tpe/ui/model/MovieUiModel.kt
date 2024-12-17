package ar.edu.unicen.tpe.ui.model

import ar.edu.unicen.tpe.dll.model.Genre
import ar.edu.unicen.tpe.dll.model.Movie

/**
 * Modelo de datos para representar una película en la interfaz de usuario.
 *
 * @param id Identificador único de la película.
 * @param title Título de la película.
 * @param overview Descripción o sinopsis de la película.
 * @param posterPath Ruta del cartel de la película.
 * @param genres Lista de géneros asociados a la película (opcional).
 */
class MovieUiModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val genres: List<Genre>?
) {

    companion object {
        /**
         * Convierte un modelo de película de la capa de datos a un modelo de película de la UI.
         *
         * @return Un objeto de tipo [MovieUiModel] que contiene la información de la película.
         */
        fun Movie.toUiModel(): MovieUiModel {
            return MovieUiModel(
                id = id, // ID de la película
                title = title, // Título de la película
                posterPath = posterPath, // Ruta del cartel
                overview = overview, // Descripción de la película
                genres = genres // Lista de géneros
            )
        }
    }
}
