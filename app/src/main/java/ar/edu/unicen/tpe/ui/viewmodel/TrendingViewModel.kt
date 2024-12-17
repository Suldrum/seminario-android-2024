package ar.edu.unicen.tpe.ui.viewmodel

import ar.edu.unicen.tpe.dll.data.MovieRepository
import ar.edu.unicen.tpe.ui.model.MovieListUiModel
import ar.edu.unicen.tpe.ui.model.MovieListUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel para gestionar la lógica relacionada con la obtención de películas populares.
 *
 * @property repository El repositorio de películas del cual se obtendrán los datos.
 */
@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val repository: MovieRepository
) : MovieViewModel<MovieListUiModel>() {

    /**
     * Obtiene datos del repositorio de películas y los transforma en un modelo adecuado para la UI.
     *
     * @param param Parámetro opcional para filtrar los resultados (actualmente no se utiliza).
     * @return Resultado que encapsula un modelo de lista de películas para la UI.
     */
    override suspend fun getRepositoryData(param: String?): Result<MovieListUiModel> {
        // Llama al repositorio para obtener las películas de tendencia de la semana y transforma los resultados.
        return repository.getTrendingMovies(timeWindow = "week").map { it.toUiModel() }
    }
}
