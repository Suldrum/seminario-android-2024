package ar.edu.unicen.tpe.ui.components.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unicen.tpe.R
import ar.edu.unicen.tpe.ui.utils.ImageUtils.getImageUrl
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

/**
 * Sección que muestra el póster de una película.
 *
 * Esta función compone una interfaz de usuario que presenta el póster
 * de la película utilizando Glide para la carga de imágenes.
 *
 * @param imagePath El path de la imagen del póster proporcionado por la API.
 *                  Si la URL no es válida, se mostrará una imagen por defecto.
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailPosterSection(imagePath: String) {
    GlideImage(
        model = getImageUrl(imagePath = imagePath) ?: R.drawable.ic_photo, // Obtener la URL de la imagen o usar imagen por defecto
        contentDescription = null, // Descripción de contenido para accesibilidad (aquí se puede agregar si se desea)
        modifier = Modifier
            .fillMaxWidth() // Ocupa todo el ancho disponible
            .height(200.dp) // Altura fija para el póster
            .padding(end = 8.dp), // Espaciado a la derecha
        contentScale = ContentScale.Crop, // Recorta la imagen para llenar el espacio
        failure = placeholder(R.drawable.ic_broken_image) // Muestra la imagen por defecto en caso de fallo
    )
}

/**
 * Vista previa de la sección del póster de la película.
 *
 * Permite visualizar cómo se verá la sección del póster en el editor de diseño,
 * utilizando una imagen de muestra.
 */
@Preview(showBackground = true)
@Composable
fun PreviewDetailPosterSection() {
    DetailPosterSection(imagePath = "/zqkmTXzjkAgXmEWLRsY4UpTWCeo.jpg") // Ejemplo de un path de imagen
}
