package ar.edu.unicen.tpe.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Clase base abstracta para ViewModels relacionados con películas.
 *
 * @param T Tipo de dato que manejará el ViewModel, que puede ser cualquier modelo específico de películas.
 */
abstract class MovieViewModel<T> : ViewModel() {
    // Estado para las películas
    private val _data = MutableStateFlow<T?>(null)
    val data = _data.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    /**
     * Método abstracto que debe ser implementado por las subclases para obtener datos del repositorio.
     *
     * @param param Parámetro opcional para filtrar los resultados.
     * @return Resultado que encapsula los datos del repositorio.
     */
    protected abstract suspend fun getRepositoryData(param: String? = null): Result<T>

    /**
     * Inicia la carga de datos desde el repositorio.
     *
     * @param param Parámetro opcional para filtrar los resultados.
     */
    fun fetchData(param: String? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = getRepositoryData(param)
                result.onSuccess { data ->
                    _data.value = data
                    _errorMessage.value = null
                }.onFailure { error ->
                    _errorMessage.value = error.message
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}