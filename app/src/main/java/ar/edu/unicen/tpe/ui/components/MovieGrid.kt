package ar.edu.unicen.tpe.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.R
import ar.edu.unicen.tpe.dll.model.Genre
import ar.edu.unicen.tpe.ui.model.MovieUiModel

/**
 * Componente que muestra una cuadrícula de películas.
 *
 * Este componente presenta una lista de películas en un formato de cuadrícula,
 * adaptándose a la orientación de la pantalla (retrato o paisaje).
 *
 * @param movies La lista de películas a mostrar. Puede ser nula o vacía.
 * @param scrollState El estado de desplazamiento de la cuadrícula.
 * @param toDetails Función que se invoca al seleccionar una película, recibiendo el ID de la película.
 */
@Composable
fun MovieGrid(
    movies: List<MovieUiModel>?,
    scrollState: LazyGridState,
    toDetails: (Int) -> Unit
) {
    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        state = scrollState,
        columns = GridCells.Fixed(if (isPortrait) 1 else 2), // Una columna en retrato, dos en paisaje
    ) {
        if (!movies.isNullOrEmpty()) {
            items(movies) { movie ->
                MovieGridItem(movie = movie, toDetails = toDetails, isPortrait = isPortrait)
            }
        } else {
            item {
                NoDataMessage(R.string.not_movies_found) // Mensaje cuando no hay películas disponibles
            }
        }
    }
}

/**
 * Vista previa de la cuadrícula de películas.
 *
 * Permite visualizar cómo se verá la cuadrícula de películas en el editor de diseño
 * utilizando una lista de películas de ejemplo.
 */
@Preview(showBackground = true)
@Composable
fun PreviewMovieGrid() {
    val sampleMovies = listOf(
        MovieUiModel(
            id = 1,
            title = "Sample Movie 1",
            overview = "This is a sample overview for movie 1.",
            posterPath = "sample_poster_1.jpg",
            genres = listOf(Genre(id = 1, name = "Action"))
        ),
        MovieUiModel(
            id = 2,
            title = "Sample Movie 2",
            overview = "This is a sample overview for movie 2.",
            posterPath = "sample_poster_2.jpg",
            genres = listOf(Genre(id = 2, name = "Adventure"))
        ),
        MovieUiModel(
            id = 3,
            title = "Sample Movie 3",
            overview = "This is a sample overview for movie 3.",
            posterPath = "sample_poster_3.jpg",
            genres = listOf(Genre(id = 3, name = "Comedy"))
        )
    )
    // Simulación de LazyGridState
    val scrollState = rememberLazyGridState()
    // Vista previa en modo retrato
    MovieGrid(movies = sampleMovies, scrollState = scrollState, toDetails = { /* No hace nada en la vista previa */ })
}
