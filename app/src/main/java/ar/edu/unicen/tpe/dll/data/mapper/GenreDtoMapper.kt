package ar.edu.unicen.tpe.dll.data.mapper

import ar.edu.unicen.tpe.dll.data.dto.GenreDto
import ar.edu.unicen.tpe.dll.model.Genre

/**
 * Convierte un objeto de tipo [GenreDto] a un modelo de dominio [Genre].
 *
 * Esta función toma un objeto `GenreDto`, que representa un género en formato de datos de transferencia,
 * y lo convierte en un objeto `Genre`, que es el modelo de dominio de la aplicación. Se asignan valores
 * predeterminados para campos nulos para garantizar que el modelo de dominio `Genre` esté completamente inicializado.
 *
 * @receiver GenreDto instancia del género en formato DTO.
 * @return Genre instancia del género en el modelo de dominio de la aplicación.
 */
fun GenreDto.toModel(): Genre {
    return Genre(
        id = id ?: 0,
        name = name ?: ""
    )
}
