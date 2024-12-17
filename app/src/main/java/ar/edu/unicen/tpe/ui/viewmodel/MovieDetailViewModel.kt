package ar.edu.unicen.tpe.ui.viewmodel

import android.util.Log
import ar.edu.unicen.tpe.dll.data.MovieRepository
import ar.edu.unicen.tpe.ui.model.MovieUiModel
import ar.edu.unicen.tpe.ui.model.MovieUiModel.Companion.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel para gestionar la lógica relacionada con los detalles de una película específica.
 *
 * @property repository El repositorio de películas del cual se obtendrán los datos.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : MovieViewModel<MovieUiModel?>() {

    /**
     * Obtiene datos del repositorio sobre una película específica basándose en su ID.
     *
     * @param param ID de la película para la cual se desean obtener los detalles.
     *              Este valor se convierte a un entero, y si no es válido, se usará null.
     * @return Resultado que encapsula un modelo de detalles de la película para la UI, o null si no se encuentra.
     */
    override suspend fun getRepositoryData(param: String?): Result<MovieUiModel?> {
        // Llama al repositorio para obtener los detalles de la película especificada por el ID.
        return repository.getMovie(param?.toIntOrNull()).map { it.toUiModel() }
    }
}
