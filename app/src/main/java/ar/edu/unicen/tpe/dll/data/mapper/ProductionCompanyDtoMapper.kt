package ar.edu.unicen.tpe.dll.data.mapper

import ar.edu.unicen.tpe.dll.data.dto.ProductionCompanyDto
import ar.edu.unicen.tpe.dll.model.ProductionCompany

/**
 * Convierte un objeto de tipo [ProductionCompanyDto] a un modelo de dominio [ProductionCompany].
 *
 * Esta función mapea los datos del DTO `ProductionCompanyDto` al modelo `ProductionCompany` de la aplicación,
 * asignando valores predeterminados en caso de que alguno de los campos sea nulo.
 *
 * @receiver ProductionCompanyDto instancia de compañía de producción en formato DTO.
 * @return ProductionCompany instancia de compañía de producción en el modelo de dominio.
 */
fun ProductionCompanyDto.toModel(): ProductionCompany {
    return ProductionCompany(
        id = id ?: 0,
        name = name ?: "",
        logoPath = logoPath ?: "",
        originCountry = originCountry ?: "",
    )
}
