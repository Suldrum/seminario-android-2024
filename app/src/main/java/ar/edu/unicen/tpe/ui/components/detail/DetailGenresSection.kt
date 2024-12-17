package ar.edu.unicen.tpe.ui.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.R
import ar.edu.unicen.tpe.dll.model.Genre
import ar.edu.unicen.tpe.ui.components.NoDataMessage

/**
 * Sección de géneros para detalles de la película.
 *
 * Esta función compone una interfaz de usuario que muestra los géneros
 * asociados a una película. Si no hay géneros disponibles, muestra un mensaje
 * indicando que no hay datos.
 *
 * @param genres Lista de objetos [Genre] que representan los géneros de la película.
 * Si la lista es nula o vacía, se mostrará un mensaje de no datos.
 */
@Composable
fun DetailGenresSection(genres: List<Genre>?) {
    if (!genres.isNullOrEmpty()) {
        Text(
            text = stringResource(id = R.string.detail_genres_title), // Título de la sección de géneros
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary // Color del título
        )
        genres.forEach { genre -> // Itera sobre cada género
            Text(
                text = genre.name ?: stringResource(id = R.string.default_no_name), // Nombre del género
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        NoDataMessage(messageRes = R.string.no_genres_available) // Mensaje de no datos
    }
}

/**
 * Vista previa de la sección de géneros.
 *
 * Permite visualizar cómo se verá la sección de géneros
 * en el editor de diseño.
 */
@Preview(showBackground = true)
@Composable
fun PreviewDetailGenresSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), // Espaciado alrededor de la columna
        verticalArrangement = Arrangement.spacedBy(8.dp) // Espaciado vertical entre elementos
    ) {
        val sampleGenres = listOf(
            Genre(id = 1, name = "Action"),      // Género de acción
            Genre(id = 2, name = "Adventure"),   // Género de aventura
            Genre(id = 3, name = "Comedy")       // Género de comedia
        )
        DetailGenresSection(genres = sampleGenres) // Renderiza la sección de géneros con datos de muestra
    }
}
