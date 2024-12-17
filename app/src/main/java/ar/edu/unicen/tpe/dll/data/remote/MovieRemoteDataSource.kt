package ar.edu.unicen.tpe.dll.data.remote

import android.util.Log
import ar.edu.unicen.tpe.dll.data.dto.MovieDto
import ar.edu.unicen.tpe.dll.data.dto.MovieListDto
import ar.edu.unicen.tpe.dll.data.mapper.toModel
import ar.edu.unicen.tpe.dll.model.Movie
import ar.edu.unicen.tpe.dll.model.MovieList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * Fuente de datos remota para acceder a la API de películas.
 *
 * Esta clase se encarga de realizar las llamadas a la API para obtener
 * información sobre películas, así como de manejar los errores de red.
 */
class MovieRemoteDataSource @Inject constructor(
    private val apiMovie: ApiMovie, // Interfaz para las llamadas a la API
    private val networkUtils: NetworkUtils // Utilidades de red para manejar errores
) {

    /**
     * Busca películas según una consulta dada.
     *
     * @param query La cadena de búsqueda para las películas.
     * @return Un objeto Result que contiene una lista de películas que coinciden con la búsqueda o un error.
     */
    suspend fun searchMovies(query: String?): Result<MovieList> {
        return getMovieList { apiMovie.searchMovies(query) }
    }

    /**
     * Obtiene una lista de películas en tendencia basadas en un período de tiempo.
     *
     * @param timeWindow El período de tiempo para las películas en tendencia (ejemplo: "day", "week").
     * @return Un objeto Result que contiene una lista de películas en tendencia o un error.
     */
    suspend fun getTrendingMovies(timeWindow: String?): Result<MovieList> {
        return getMovieList { apiMovie.getTrendingMovies(timeWindow) }
    }

    /**
     * Obtiene una lista de películas similares a una película específica.
     *
     * @param movieId El ID de la película para la cual se desean obtener películas similares.
     * @return Un objeto Result que contiene una lista de películas similares o un error.
     */
    suspend fun getSimilarMovies(movieId: Int?): Result<MovieList> {
        return getMovieList { apiMovie.getSimilarMovies(movieId.toString()) }
    }

    /**
     * Obtiene recomendaciones de películas basadas en una película específica.
     *
     * @param movieId El ID de la película para la cual se desean obtener recomendaciones.
     * @return Un objeto Result que contiene una lista de películas recomendadas o un error.
     */
    suspend fun getRecommendationsMovies(movieId: Int?): Result<MovieList> {
        return getMovieList { apiMovie.getRecommendationsMovies(movieId.toString()) }
    }

    /**
     * Obtiene una lista de películas populares.
     *
     * @return Un objeto Result que contiene una lista de películas populares o un error.
     */
    suspend fun getPopularMovies(): Result<MovieList> {
        return getMovieList { apiMovie.getPopularMovies() }
    }

    /**
     * Obtiene el detalle de una película específica.
     *
     * @param movieId El ID de la película que se desea obtener.
     * @return Un objeto Result que contiene la película obtenida o un error.
     */
    suspend fun getMovie(movieId: Int?): Result<Movie> {
        return withContext(Dispatchers.IO) { // Realiza la operación en un hilo de IO
            try {
                val response: Response<MovieDto> = apiMovie.getMovie(movieId) // Llama a la API
                if (response.isSuccessful) {
                    // Convierte el resultado a un modelo y lo devuelve como éxito
                    Result.success(response.body()?.toModel() ?: Movie())
                } else {
                    // Maneja el error basado en el código de respuesta
                    val errorMessage = networkUtils.handleHttpError(response.code())
                    Result.failure(Exception(errorMessage))
                }
            } catch (e: UnknownHostException) {
                // Maneja la falta de conexión a Internet
                val errorMessage = networkUtils.handleHttpError(-1009)
                Result.failure(Exception(errorMessage))
            } catch (e: HttpException) {
                // Maneja errores HTTP específicos
                val errorMessage = networkUtils.handleHttpError(e.code())
                Result.failure(Exception(errorMessage))
            } catch (e: IOException) {
                // Maneja errores de entrada/salida
                val errorMessage = networkUtils.handleHttpError(-2)
                Result.failure(Exception(errorMessage))
            } catch (e: NullPointerException) {
                // Maneja errores de puntero nulo
                val errorMessage = networkUtils.handleHttpError(-3)
                Result.failure(Exception(errorMessage))
            } catch (e: IllegalArgumentException) {
                // Maneja argumentos inválidos
                val errorMessage = networkUtils.handleHttpError(404)
                Result.failure(Exception(errorMessage))
            } catch (e: Exception) {
            //    Log.e("getMovie", "Error calling getMovie: ${e.message}", e)
                // Maneja cualquier otro tipo de excepción
                val errorMessage = networkUtils.handleHttpError(-1)
                Result.failure(Exception(errorMessage))
            }
        }
    }

    /**
     * Método genérico para obtener listas de películas.
     *
     * Este método toma una llamada a la API y maneja la respuesta.
     *
     * @param apiCall Una función que realiza la llamada a la API.
     * @return Un objeto Result que contiene la lista de películas o un error.
     */
    private suspend fun getMovieList(apiCall: suspend () -> Response<MovieListDto>): Result<MovieList> {
        return withContext(Dispatchers.IO) { // Realiza la operación en un hilo de IO
            try {
                val response = apiCall() // Llama a la función pasada como parámetro
                if (response.isSuccessful) {
                    // Convierte el resultado a un modelo y lo devuelve como éxito
                    val movieList = response.body()?.toModel() ?: MovieList()
                    Result.success(movieList)
                } else {
                    // Maneja el error basado en el código de respuesta
                    val errorMessage = networkUtils.handleHttpError(response.code())
                    Result.failure(Exception(errorMessage))
                }
            } catch (e: UnknownHostException) {
                // Maneja la falta de conexión a Internet
                val errorMessage = networkUtils.handleHttpError(-1009)
                Result.failure(Exception(errorMessage))
            } catch (e: HttpException) {
                // Maneja errores HTTP específicos
                val errorMessage = networkUtils.handleHttpError(e.code())
                Result.failure(Exception(errorMessage))
            } catch (e: IOException) {
                // Maneja errores de entrada/salida
                val errorMessage = networkUtils.handleHttpError(-2)
                Result.failure(Exception(errorMessage))
            } catch (e: NullPointerException) {
                // Maneja errores de puntero nulo
                val errorMessage = networkUtils.handleHttpError(-3)
                Result.failure(Exception(errorMessage))
            } catch (e: IllegalArgumentException) {
                // Maneja argumentos inválidos
                val errorMessage = networkUtils.handleHttpError(404)
                Result.failure(Exception(errorMessage))
            } catch (e: Exception) {
                // Maneja cualquier otro tipo de excepción
                val errorMessage = networkUtils.handleHttpError(-1)
                Result.failure(Exception(errorMessage))
            }
        }
    }
}
