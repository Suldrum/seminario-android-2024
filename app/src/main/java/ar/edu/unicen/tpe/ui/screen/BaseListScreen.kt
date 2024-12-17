package ar.edu.unicen.tpe.ui.screen

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ar.edu.unicen.tpe.ui.components.MovieGrid
import ar.edu.unicen.tpe.ui.model.MovieListUiModel
import ar.edu.unicen.tpe.ui.viewmodel.MovieViewModel

@Composable
fun BaseListScreen(
    viewModel: MovieViewModel<MovieListUiModel>,
    toDetails: (Int) -> Unit
) {
    val scrollState = rememberLazyGridState()
    LaunchedEffect(Unit) {
        viewModel.fetchData()
    }

    BaseScreen(
        viewModel = viewModel,
        onRetry = { viewModel.fetchData() },
        content = { data ->
            MovieGrid(
                movies = data?.results,
                scrollState = scrollState,
                toDetails = toDetails
            )
        }
    )
}