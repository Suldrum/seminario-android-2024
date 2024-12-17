package ar.edu.unicen.tpe.ui.viewmodel

import ar.edu.unicen.tpe.dll.data.MovieRepository
import ar.edu.unicen.tpe.ui.model.MovieListUiModel
import ar.edu.unicen.tpe.ui.model.MovieListUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel para gestionar la lógica relacionada con la obtención de películas similares.
 *
 * @property repository El repositorio de películas del cual se obtendrán los datos.
 */
@HiltViewModel
class SimilarViewModel @Inject constructor(
    private val repository: MovieRepository
) : MovieViewModel<MovieListUiModel>() {

    /**
     * Obtiene datos del repositorio de películas similares basándose en el ID de una película específica.
     *
     * @param param ID de la película para la cual se desean obtener películas similares.
     *              Este valor se convierte a un entero, y si no es válido, se usará null.
     * @return Resultado que encapsula un modelo de lista de películas similares para la UI.
     */
    override suspend fun getRepositoryData(param: String?): Result<MovieListUiModel> {
        // Llama al repositorio para obtener las películas similares a la película especificada por el ID.
        return repository.getSimilarMovies(param?.toIntOrNull()).map { it.toUiModel() }
    }
}
