package ar.edu.unicen.tpe.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.unicen.tpe.ui.screen.MovieDetailScreen
import ar.edu.unicen.tpe.ui.screen.HomeScreen
import ar.edu.unicen.tpe.ui.screen.TrendingScreen
import ar.edu.unicen.tpe.ui.viewmodel.MovieDetailViewModel
import ar.edu.unicen.tpe.ui.viewmodel.PopularMoviesViewModel
import ar.edu.unicen.tpe.ui.viewmodel.RecommendationsViewModel
import ar.edu.unicen.tpe.ui.viewmodel.SimilarViewModel
import ar.edu.unicen.tpe.ui.viewmodel.TrendingViewModel

/**
 * Composable que define la gráfica de navegación para la aplicación.
 *
 * Esta función establece las diferentes pantallas de la aplicación y maneja la navegación entre ellas.
 *
 * @param navController Controlador de navegación que maneja la navegación entre pantallas.
 * @param paddingValues Espacios de relleno que se aplicarán a la gráfica de navegación.
 */
@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    // Se define el host de navegación con la ruta de inicio establecida en la pantalla principal (Home).
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        // Pantalla principal (Home)
        composable(BottomNavItem.Home.route) {
            val viewModel: PopularMoviesViewModel = hiltViewModel()
            HomeScreen(
                viewModel = viewModel,
                toDetails = { movieId ->
                    // Navega a la pantalla de detalles de la película usando el ID de la película.
                    navController.navigate("movie/$movieId")
                }
            )
        }

        // Pantalla de películas en tendencia
        composable(BottomNavItem.Trending.route) {
            val viewModel: TrendingViewModel = hiltViewModel()
            TrendingScreen(
                viewModel = viewModel,
                toDetails = { movieId ->
                    // Navega a la pantalla de detalles de la película usando el ID de la película.
                    navController.navigate("movie/$movieId")
                }
            )
        }

        // Pantalla de detalles de la película
        composable("movie/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            // Verifica si el ID de la película es válido y mayor que cero.
            if (movieId?.toIntOrNull() != null && movieId.toIntOrNull()!! > 0) {
                val viewModel: MovieDetailViewModel = hiltViewModel()
                val listMovieModel: SimilarViewModel = hiltViewModel()
                // Se puede comentar o descomentar la línea siguiente para usar recomendaciones si es necesario.
                // val listMovieModel: RecommendationsViewModel = hiltViewModel()
                MovieDetailScreen(
                    viewModel = viewModel,
                    navController = navController,
                    movieId =  movieId,
                    listMovieModel = listMovieModel,
                )
            } else {
                // Si el ID de la película no es válido, se vuelve a la pantalla anterior.
                navController.popBackStack()
            }
        }
    }
}


