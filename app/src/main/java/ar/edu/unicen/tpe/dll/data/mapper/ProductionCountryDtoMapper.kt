package ar.edu.unicen.tpe.dll.data.mapper

import ar.edu.unicen.tpe.dll.data.dto.ProductionCountryDto
import ar.edu.unicen.tpe.dll.model.ProductionCountry

/**
 * Convierte un objeto de tipo [ProductionCountryDto] a un modelo de dominio [ProductionCountry].
 *
 * Esta función mapea los datos del DTO `ProductionCountryDto` al modelo `ProductionCountry` de la aplicación,
 * asegurando que se proporcionen valores predeterminados para los campos que podrían ser nulos.
 *
 * @receiver ProductionCountryDto instancia de país de producción en formato DTO.
 * @return ProductionCountry instancia de país de producción en el modelo de dominio.
 */
fun ProductionCountryDto.toModel(): ProductionCountry {
    return ProductionCountry(
        iso3166_1 = iso3166_1 ?: "",
        name = name ?: "",
    )
}
