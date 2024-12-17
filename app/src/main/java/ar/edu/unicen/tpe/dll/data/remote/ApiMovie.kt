package ar.edu.unicen.tpe.dll.data.remote

import ar.edu.unicen.tpe.dll.data.dto.MovieDto
import ar.edu.unicen.tpe.dll.data.dto.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response

/**
 * Interfaz que define los endpoints de la API para obtener información sobre películas.
 *
 * Utiliza Retrofit para manejar las solicitudes HTTP y devuelve respuestas encapsuladas
 * en objetos de tipo Response.
 */
interface ApiMovie {

    /**
     * Obtiene una lista de películas populares.
     *
     * @return Un objeto Response que contiene una lista de películas populares en formato MovieListDto.
     */
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieListDto>

    /**
     * Obtiene los detalles de una película específica.
     *
     * @param movieId El ID de la película que se desea obtener.
     * @return Un objeto Response que contiene los detalles de la película en formato MovieDto.
     */
    @GET("movie/{id}")
    suspend fun getMovie(@Path("id") movieId: Int?): Response<MovieDto>

    /**
     * Busca películas según una consulta dada.
     *
     * @param query La cadena de búsqueda para las películas.
     * @return Un objeto Response que contiene una lista de películas que coinciden con la búsqueda en formato MovieListDto.
     */
    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String?): Response<MovieListDto>

    /**
     * Obtiene una lista de películas en tendencia basadas en un período de tiempo.
     *
     * @param timeWindow El período de tiempo para las películas en tendencia (ejemplo: "day", "week").
     * @return Un objeto Response que contiene una lista de películas en tendencia en formato MovieListDto.
     */
    @GET("trending/movie/{time_window}")
    suspend fun getTrendingMovies(@Path("time_window") timeWindow: String?): Response<MovieListDto>

    /**
     * Obtiene una lista de películas similares a una película específica.
     *
     * @param query El ID de la película para la cual se desean obtener películas similares.
     * @return Un objeto Response que contiene una lista de películas similares en formato MovieListDto.
     */
    @GET("movie/{id}/similar")
    suspend fun getSimilarMovies(@Path("id") query: String?): Response<MovieListDto>

    /**
     * Obtiene recomendaciones de películas basadas en una película específica.
     *
     * @param query El ID de la película para la cual se desean obtener recomendaciones.
     * @return Un objeto Response que contiene una lista de películas recomendadas en formato MovieListDto.
     */
    @GET("movie/{id}/recommendations")
    suspend fun getRecommendationsMovies(@Path("id") query: String?): Response<MovieListDto>
}
