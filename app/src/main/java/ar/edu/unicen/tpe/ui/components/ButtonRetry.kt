package ar.edu.unicen.tpe.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.unicen.tpe.R

/**
 * Componente de botón de reintento.
 *
 * Este botón se utiliza para permitir al usuario intentar una acción nuevamente,
 * como volver a cargar datos después de un error.
 *
 * @param onRetry Función que se ejecuta cuando se hace clic en el botón.
 */
@Composable
fun RetryButton(onRetry: () -> Unit) {
    Button(onClick = onRetry) {
        Text(text = stringResource(R.string.btn_retry)) // Muestra el texto del botón utilizando recursos de cadena
    }
}

/**
 * Vista previa del botón de reintento.
 *
 * Esta función permite visualizar cómo se verá el botón de reintento
 * en el editor de diseño.
 */
@Preview(showBackground = true)
@Composable
fun PreviewRetryButton() {
    RetryButton(onRetry = { /* Acción de reintento */ }) // Acción de reintento placeholder
}
