package ar.edu.unicen.tpe.dll.data.mapper

import ar.edu.unicen.tpe.dll.data.dto.SpokenLanguageDto
import ar.edu.unicen.tpe.dll.model.SpokenLanguage

/**
 * Convierte un objeto de tipo [SpokenLanguageDto] a un modelo de dominio [SpokenLanguage].
 *
 * Esta función mapea los datos del DTO `SpokenLanguageDto` al modelo `SpokenLanguage` de la aplicación,
 * asegurando que se proporcionen valores predeterminados para los campos que podrían ser nulos.
 *
 * @receiver SpokenLanguageDto instancia de idioma hablado en formato DTO.
 * @return SpokenLanguage instancia de idioma hablado en el modelo de dominio.
 */
fun SpokenLanguageDto.toModel(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName ?: "",
        iso639_1 = iso639_1 ?: "",
        name = name ?: ""
    )
}
