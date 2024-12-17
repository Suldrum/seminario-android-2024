package ar.edu.unicen.tpe.ui.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.R
import ar.edu.unicen.tpe.dll.model.Genre
import ar.edu.unicen.tpe.ui.components.MovieListItemRow
import ar.edu.unicen.tpe.ui.components.NoDataMessage
import ar.edu.unicen.tpe.ui.model.MovieUiModel

/**
 * Sección que muestra una lista de películas relacionadas.
 *
 * Esta función compone una interfaz de usuario que presenta una lista de
 * películas en forma de filas. Si la lista de películas es nula, se muestra
 * un mensaje indicando que no se encontraron contenidos relacionados.
 *
 * @param movies Lista de objetos [MovieUiModel] que representan las películas.
 * @param toDetails Función lambda que se invoca para navegar a los detalles de la película seleccionada.
 */
@Composable
fun DetailMoviesSection(
    movies: List<MovieUiModel>?,
    toDetails: (Int) -> Unit
) {
    if (movies != null ) {
        Column(
            modifier = Modifier.fillMaxWidth(), // Se llena todo el ancho disponible
            verticalArrangement = Arrangement.spacedBy(16.dp) // Espaciado entre las filas de películas
        ) {
            movies.forEach { movie ->
                MovieListItemRow(
                    movie = movie,
                    toDetails = toDetails // Función de navegación al detalle de la película
                )
            }
        }
    } else {
        NoDataMessage(messageRes = R.string.no_related_content_found) // Mensaje de no datos
    }
}

/**
 * Vista previa de la sección de películas relacionadas.
 *
 * Permite visualizar cómo se verá la sección de películas
 * en el editor de diseño, utilizando datos de muestra.
 */
@Preview(showBackground = true)
@Composable
fun PreviewDetailMoviesSection() {
    val sampleMovies = listOf(
        MovieUiModel(
            id = 1,
            title = "Sample Movie 1",
            overview = "Overview of sample movie 1.",
            posterPath = "sample_poster_1.jpg",
            genres = listOf(Genre(id = 1, name = "Action")) // Géneros de la película
        ),
        MovieUiModel(
            id = 2,
            title = "Sample Movie 2",
            overview = "Overview of sample movie 2.",
            posterPath = "sample_poster_2.jpg",
            genres = listOf(Genre(id = 2, name = "Adventure")) // Géneros de la película
        )
    )

    DetailMoviesSection(movies = sampleMovies) { movieId ->
        // Acción de navegación a detalles de la película (puedes dejarlo vacío para la vista previa)
    }
}
