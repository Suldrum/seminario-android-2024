package ar.edu.unicen.tpe.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.unicen.tpe.R
import ar.edu.unicen.tpe.ui.components.detail.DetailDataSection
import ar.edu.unicen.tpe.ui.components.detail.DetailMoviesSection
import ar.edu.unicen.tpe.ui.viewmodel.MovieViewModel
import ar.edu.unicen.tpe.ui.viewmodel.MovieDetailViewModel
import ar.edu.unicen.tpe.ui.components.NoDataMessage
import ar.edu.unicen.tpe.ui.model.MovieListUiModel

/**
 * Composable que representa la pantalla de detalles de una película.
 * Utiliza un ViewModel para obtener los detalles de la película y una lista de películas relacionadas.
 *
 * @param viewModel El ViewModel que contiene los datos de detalles de la película.
 * @param listMovieModel El ViewModel que contiene la lista de películas relacionadas.
 * @param navController El controlador de navegación para la navegación entre pantallas.
 * @param movieId El identificador de la película a mostrar.
 */
@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    listMovieModel: MovieViewModel<MovieListUiModel> = hiltViewModel(),
    navController: NavController,
    movieId: String
) {
    // Recordar el estado del scroll para mantener la posición en la lista de películas
    val scrollState = rememberLazyListState()

    // Cargar los datos cuando el ID de la película cambie
    LaunchedEffect(movieId) {
        viewModel.fetchData(param = movieId)
        listMovieModel.fetchData(param = movieId)
    }

    /**
     * Función que reintenta cargar los datos en caso de error.
     */
    fun retryFetch() {
        viewModel.fetchData(param = movieId)
        listMovieModel.fetchData(param = movieId)
    }

    // Cargar el contenido de la pantalla usando BaseScreen
    BaseScreen(
        viewModel = viewModel,
        onRetry = { retryFetch() },
        content = { movie ->
            // Si se han encontrado datos de la película
            if (movie != null && movie.id != 0) {
                LazyColumn(
                    state = scrollState,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Sección para mostrar los detalles de la película
                    item {
                        DetailDataSection(movie = movie)
                    }
                    // Sección para mostrar las películas relacionadas
                    item {
                        val movies by listMovieModel.data.collectAsState()
                        DetailMoviesSection(
                            movies = movies?.results,
                            toDetails = { movieId ->
                                navController.navigate("movie/$movieId")
                            }
                        )
                    }
                }
            } else {
                // Si no se encuentra la película, muestra un mensaje de "No encontrado"
                NoDataMessage(R.string.not_movie_found)
            }
        }
    )
}
