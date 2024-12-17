package ar.edu.unicen.tpe.ui.viewmodel

import ar.edu.unicen.tpe.dll.data.MovieRepository
import ar.edu.unicen.tpe.ui.model.MovieListUiModel
import ar.edu.unicen.tpe.ui.model.MovieListUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel para gestionar la lógica relacionada con las recomendaciones de películas.
 *
 * @property repository El repositorio de películas del cual se obtendrán los datos.
 */
@HiltViewModel
class RecommendationsViewModel @Inject constructor(
    private val repository: MovieRepository
) : MovieViewModel<MovieListUiModel>() {

    /**
     * Obtiene datos del repositorio de películas recomendadas basándose en el ID de una película específica.
     *
     * @param param ID de la película para la cual se desean obtener recomendaciones.
     *              Este valor se convierte a un entero, y si no es válido, se usará null.
     * @return Resultado que encapsula un modelo de lista de películas recomendadas para la UI.
     */
    override suspend fun getRepositoryData(param: String?): Result<MovieListUiModel> {
        // Llama al repositorio para obtener las recomendaciones en base a la película especificada por el ID.
        return repository.getRecommendationsMovies(param?.toIntOrNull()).map { it.toUiModel() }
    }
}


