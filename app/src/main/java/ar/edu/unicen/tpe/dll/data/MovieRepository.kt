package ar.edu.unicen.tpe.dll.data

import ar.edu.unicen.tpe.dll.data.remote.MovieRemoteDataSource
import ar.edu.unicen.tpe.dll.model.Movie
import ar.edu.unicen.tpe.dll.model.MovieList
import javax.inject.Inject

/**
 * Repositorio para gestionar el acceso a los datos de las películas.
 *
 * Este repositorio actúa como una capa de abstracción entre la fuente de datos remota
 * y el resto de la aplicación, facilitando la obtención de información sobre películas.
 */
class MovieRepository @Inject constructor(
    private val remoteData: MovieRemoteDataSource,
) {
    /**
     * Obtiene los detalles de una película específica.
     *
     * @param movieId El ID de la película que se desea obtener.
     * @return Un objeto Result que contiene la película obtenida o un error.
     */
    suspend fun getMovie(movieId: Int?): Result<Movie> {
        return remoteData.getMovie(movieId)
    }

    /**
     * Obtiene una lista de películas populares.
     *
     * @return Un objeto Result que contiene una lista de películas populares o un error.
     */
    suspend fun getPopularMovies(): Result<MovieList> {
        return remoteData.getPopularMovies()
    }

    /**
     * Busca películas según una consulta dada.
     *
     * @param query La cadena de búsqueda para las películas.
     * @return Un objeto Result que contiene una lista de películas que coinciden con la búsqueda o un error.
     */
    suspend fun searchMovies(query: String?): Result<MovieList> {
        return remoteData.searchMovies(query)
    }

    /**
     * Obtiene una lista de películas en tendencia basadas en un período de tiempo.
     *
     * @param timeWindow El período de tiempo para las películas en tendencia (ejemplo: "day", "week").
     * @return Un objeto Result que contiene una lista de películas en tendencia o un error.
     */
    suspend fun getTrendingMovies(timeWindow: String?): Result<MovieList> {
        return remoteData.getTrendingMovies(timeWindow)
    }

    /**
     * Obtiene una lista de películas similares a una película específica.
     *
     * @param movieId El ID de la película para la cual se desean obtener películas similares.
     * @return Un objeto Result que contiene una lista de películas similares o un error.
     */
    suspend fun getSimilarMovies(movieId: Int?): Result<MovieList> {
        return remoteData.getSimilarMovies(movieId)
    }

    /**
     * Obtiene recomendaciones de películas basadas en una película específica.
     *
     * @param movieId El ID de la película para la cual se desean obtener recomendaciones.
     * @return Un objeto Result que contiene una lista de películas recomendadas o un error.
     */
    suspend fun getRecommendationsMovies(movieId: Int?): Result<MovieList> {
        return remoteData.getRecommendationsMovies(movieId)
    }
}
