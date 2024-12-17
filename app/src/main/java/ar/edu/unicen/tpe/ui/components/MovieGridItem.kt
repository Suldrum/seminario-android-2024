package ar.edu.unicen.tpe.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.dll.model.Genre
import ar.edu.unicen.tpe.ui.model.MovieUiModel

/**
 * Componente que representa un ítem de película en la cuadrícula.
 *
 * Este componente decide cómo mostrar un ítem de película en función de la orientación
 * de la pantalla, utilizando un diseño de fila para retrato y un diseño de columna
 * para paisaje.
 *
 * @param movie La película que se mostrará en el ítem.
 * @param toDetails Función que se invoca al seleccionar la película, recibiendo el ID de la película.
 * @param isPortrait Indica si la orientación de la pantalla es retrato.
 */
@Composable
fun MovieGridItem(
    movie: MovieUiModel,
    toDetails: (Int) -> Unit,
    isPortrait: Boolean
) {
    if (isPortrait) {
        MovieListItemRow(movie = movie, toDetails = toDetails) // Diseño en fila para orientación retrato
    } else {
        MovieListItemColumn(movie = movie, toDetails = toDetails) // Diseño en columna para orientación paisaje
    }
}

/**
 * Vista previa del ítem de película en la cuadrícula.
 *
 * Permite visualizar cómo se verá un ítem de película en ambos modos de orientación:
 * retrato y paisaje, utilizando un ejemplo de película.
 */
@Preview(showBackground = true)
@Composable
fun PreviewMovieGridItem() {
    val sampleMovie = MovieUiModel(
        id = 1,
        title = "Sample Movie Title",
        overview = "This is a sample overview.",
        posterPath = "sample_poster.jpg",
        genres = listOf(Genre(id = 1, name = "Action"))
    )

    // Vista previa en modo retrato
    MovieGridItem(movie = sampleMovie, toDetails = { /* No hace nada en la vista previa */ }, isPortrait = true)

    // Agregar un separador o espacio entre las previas
    Spacer(modifier = Modifier.height(16.dp))

    // Vista previa en modo paisaje
    MovieGridItem(movie = sampleMovie, toDetails = { /* No hace nada en la vista previa */ }, isPortrait = false)
}
