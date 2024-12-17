package ar.edu.unicen.tpe.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.R

/**
 * Componente que muestra un mensaje de "sin datos" en la interfaz de usuario.
 *
 * Este componente utiliza un recurso de cadena para mostrar un mensaje centrado
 * que informa al usuario que no hay datos disponibles. El mensaje se estiliza
 * para adaptarse al tema actual de la aplicación.
 *
 * @param messageRes Recurso de cadena que representa el mensaje a mostrar.
 */
@Composable
fun NoDataMessage(@StringRes messageRes: Int) {
    Text(
        text = stringResource(id = messageRes),
        modifier = Modifier
            .fillMaxWidth() // Ocupar todo el ancho disponible
            .padding(16.dp), // Padding alrededor del texto
        textAlign = TextAlign.Center, // Centrar el texto
        style = MaterialTheme.typography.bodyLarge, // Estilo de texto del tema actual
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f) // Color del texto
    )
}

/**
 * Vista previa del mensaje "sin datos".
 *
 * Permite visualizar cómo se verá el mensaje utilizando un ejemplo de recurso de cadena.
 */
@Preview(showBackground = true)
@Composable
fun PreviewNoDataMessage() {
    NoDataMessage(messageRes = R.string.no_related_content_found) // Reemplaza con un recurso de cadena válido
}
