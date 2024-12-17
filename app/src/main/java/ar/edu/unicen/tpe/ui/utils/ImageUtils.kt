package ar.edu.unicen.tpe.ui.utils

import ar.edu.unicen.tpe.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Módulo de utilidades para el manejo de imágenes.
 * Proporciona métodos para generar URLs de imágenes basadas en la configuración y el path de la imagen.
 */
@Module
@InstallIn(SingletonComponent::class)
object ImageUtils {
    private const val IMAGE_BASE_URL = BuildConfig.IMAGE_BASE_URL
    private const val IMAGE_FULL_SIZE = "original"

    /**
     * Genera una URL de imagen completa a partir del tamaño y el path proporcionados.
     *
     * @param imagePath El path de la imagen proporcionado por la API (ejemplo: "/kqjL17yufvn9OVLyXYpvtyrFfak.jpg").
     * @param size El tamaño de la imagen (por defecto será `IMAGE_FULL_SIZE`).
     * @return URL completa de la imagen o null si el path está vacío o en blanco.
     */
    @Provides
    @Singleton
    fun getImageUrl(imagePath: String = "", size: String = IMAGE_FULL_SIZE): String? {
        return when {
            imagePath.isBlank() -> null // Retorna null si el path está vacío
            imagePath.endsWith(".svg") -> "$IMAGE_BASE_URL$IMAGE_FULL_SIZE$imagePath" // Siempre tamaño completo para SVG
            else -> "$IMAGE_BASE_URL$size$imagePath" // Tamaño variable para otros formatos
        }
    }
}