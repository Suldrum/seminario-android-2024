package ar.edu.unicen.tpe.ui.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.R
import ar.edu.unicen.tpe.dll.model.Genre
import ar.edu.unicen.tpe.ui.model.MovieUiModel

/**
 * Sección de datos de detalle para una película.
 *
 * Esta función compone una interfaz de usuario que muestra información detallada
 * sobre una película, incluyendo su título, descripción, y géneros.
 *
 * @param movie Objeto [MovieUiModel] que contiene los detalles de la película a mostrar.
 */
@Composable
fun DetailDataSection(movie: MovieUiModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Espaciado alrededor de la columna
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaciado vertical entre elementos
    ) {
        DetailPosterSection(movie.posterPath) // Sección del cartel de la película
        Text(
            text = movie.title ?: stringResource(id = R.string.default_no_title), // Título de la película
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground // Color del texto
        )

        Text(
            text = movie.overview ?: stringResource(id = R.string.default_no_overview), // Descripción de la película
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground // Color del texto
        )

        DetailGenresSection(genres = movie.genres) // Sección de géneros de la película
    }
}

/**
 * Vista previa de la sección de datos de detalle.
 *
 * Permite visualizar cómo se verá la sección de detalle
 * en el editor de diseño.
 */
@Preview(showBackground = true)
@Composable
fun PreviewDetailDataSection() {
    val sampleGenres = listOf(
        Genre(id = 1, name = "Action"),      // Género de acción
        Genre(id = 2, name = "Adventure"),   // Género de aventura
        Genre(id = 3, name = "Comedy")       // Género de comedia
    )

    val sampleMovie = MovieUiModel(
        id = 1,
        title = "Sample Movie",                             // Título de la película de muestra
        overview = "This is a sample movie overview.",      // Descripción de la película de muestra
        posterPath = "",                                   // Ruta del cartel (vacío para la vista previa)
        genres = sampleGenres                              // Lista de géneros de la película de muestra
    )
    DetailDataSection(movie = sampleMovie) // Renderiza la sección de detalles con datos de muestra
}
