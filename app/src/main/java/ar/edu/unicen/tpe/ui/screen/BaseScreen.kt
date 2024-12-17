package ar.edu.unicen.tpe.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ar.edu.unicen.tpe.ui.components.RetryButton
import ar.edu.unicen.tpe.ui.viewmodel.MovieViewModel

/**
 * Composable de pantalla base que proporciona manejo de carga, error y datos para la UI.
 * Observa el estado de un [MovieViewModel] para gestionar la visualización de un indicador
 * de carga, mensaje de error con un botón de reintento, o el contenido principal.
 *
 * @param T El tipo de datos gestionados por el ViewModel.
 * @param viewModel El ViewModel responsable de proporcionar los datos y la información de estado.
 * @param content La función composable para mostrar el contenido principal cuando hay datos disponibles.
 * @param onRetry La acción a ejecutar cuando el usuario selecciona "Reintentar" después de un error.
 */
@Composable
fun <T> BaseScreen(
    viewModel: MovieViewModel<T>,
    content: @Composable (data: T?) -> Unit,
    onRetry: () -> Unit
) {
    // Estado que indica si la pantalla está en modo de carga
    val isLoading by viewModel.isLoading.collectAsState()
    // Estado que contiene el mensaje de error en caso de que ocurra un problema
    val errorMessage by viewModel.errorMessage.collectAsState()
    // Estado que contiene los datos a mostrar cuando están disponibles
    val data by viewModel.data.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            // Si está cargando, muestra el indicador de progreso circular
            isLoading -> {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
            // Si hay un mensaje de error, lo muestra junto con el botón de reintento
            errorMessage != null -> {
                errorMessage?.let { error ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = error, color = MaterialTheme.colorScheme.error)
                        RetryButton(onRetry = onRetry)
                    }
                }
            }
            // En cualquier otro caso, muestra el contenido proporcionado
            else -> {
                content(data)
            }
        }
    }
}
