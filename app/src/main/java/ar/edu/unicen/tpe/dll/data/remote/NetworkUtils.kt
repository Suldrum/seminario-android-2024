package ar.edu.unicen.tpe.dll.data.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import ar.edu.unicen.tpe.R

/**
 * Clase utilitaria para manejar errores de red y codigos de respuesta HTTP.
 *
 * Esta clase proporciona un método para mapear códigos de respuesta HTTP a mensajes de error amigables
 * para el usuario, utilizando recursos de cadena definidos en la aplicación.
 *
 * @property context El contexto de la aplicación, inyectado mediante Dagger Hilt.
 */
@Singleton
class NetworkUtils @Inject constructor(
    @ApplicationContext private val context: Context // Inyección del contexto de la aplicación
) {
    /**
     * Maneja los errores HTTP devolviendo un mensaje correspondiente basado en el código de respuesta.
     *
     * @param responseCode El código de respuesta HTTP que se desea manejar.
     * @return Un mensaje de error amigable basado en el código de respuesta.
     */
    fun handleHttpError(responseCode: Int): String {
        return when (responseCode) {
            -1 -> context.getString(R.string.generic_error_message)
            -2 -> context.getString(R.string.io_error_message)
            -3 -> context.getString(R.string.null_pointer_error)
            -1009 -> context.getString(R.string.not_internet_connection)
            200 -> context.getString(R.string.error_200_entry_not_found)
            400 -> context.getString(R.string.error_400_invalid_page)
            401 -> context.getString(R.string.error_401_auth_failed)
            404 -> context.getString(R.string.error_404_resource_not_found)
            405 -> context.getString(R.string.error_405_invalid_format)
            422 -> context.getString(R.string.error_422_invalid_parameters)
            429 -> context.getString(R.string.error_429_request_limit)
            500 -> context.getString(R.string.error_500_invalid_id)
            501 -> context.getString(R.string.error_501)
            503 -> context.getString(R.string.error_503_service_offline)
            504 -> context.getString(R.string.error_504_timeout)
            else -> context.getString(R.string.unexpected_error, responseCode)
        }
    }
}
