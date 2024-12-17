package ar.edu.unicen.tpe.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.R
import ar.edu.unicen.tpe.dll.model.Genre
import ar.edu.unicen.tpe.ui.model.MovieUiModel
import ar.edu.unicen.tpe.ui.utils.ImageUtils.getImageUrl
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

/**
 * Componente que representa un ítem de película en una disposición de fila.
 *
 * Este componente muestra la imagen del póster de la película y su título en una
 * fila. Es clickeable y permite la navegación a los detalles de la película.
 *
 * @param movie La película que se mostrará en el ítem.
 * @param toDetails Función que se invoca al seleccionar la película, recibiendo el ID de la película.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieListItemRow(movie: MovieUiModel, toDetails: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { toDetails(movie.id) } // Acción al hacer clic en el ítem
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = getImageUrl(imagePath = movie.posterPath) ?: R.drawable.ic_photo,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp) // Tamaño del ícono de la película
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop,
            failure = placeholder(R.drawable.ic_broken_image) // Imagen de placeholder en caso de error
        )

        Text(
            text = movie.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f) // Permitir que el texto ocupe el espacio restante
        )
    }
}

/**
 * Vista previa del ítem de película en una disposición de fila.
 *
 * Permite visualizar cómo se verá un ítem de película utilizando un ejemplo de película.
 */
@Preview(showBackground = true)
@Composable
fun PreviewMovieListItemRow() {
    val sampleMovie = MovieUiModel(
        id = 1,
        title = "Sample Movie Title",
        overview = "This is a sample overview.",
        posterPath = "sample_poster.jpg",
        genres = listOf(Genre(id = 1, name = "Action"))
    )

    MovieListItemRow(movie = sampleMovie) { movieId ->
        // Acción de navegación a detalles de la película (puedes dejarlo vacío para la vista previa)
    }
}
