package ar.edu.unicen.tpe.di

import ar.edu.unicen.tpe.BuildConfig
import ar.edu.unicen.tpe.dll.data.remote.ApiMovie
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import javax.inject.Singleton

/**
 * Módulo de configuración de la red.
 *
 * Proporciona instancias de OkHttpClient, Retrofit y ApiMovie,
 * utilizando la inyección de dependencias a través de Dagger Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = BuildConfig.BASE_URL
    private const val TOKEN = BuildConfig.TOKEN

    /**
     * Proporciona una instancia de [OkHttpClient] configurada con un interceptor.
     *
     * Este interceptor añade un encabezado de autorización y un parámetro de lenguaje
     * a cada solicitud de red.
     *
     * @return Instancia de [OkHttpClient].
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val language = Locale.getDefault().language
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $TOKEN")
                    .url(chain.request().url().newBuilder()
                        .addQueryParameter("language", language)
                        .build())
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    /**
     * Proporciona una instancia de [Retrofit] configurada con la URL base y el cliente HTTP.
     *
     * @param okHttpClient Cliente HTTP que se utilizará para las solicitudes.
     * @return Instancia de [Retrofit].
     */
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Proporciona una instancia de [ApiMovie] a través de la instancia de [Retrofit].
     *
     * @param retrofit Instancia de Retrofit que se utiliza para crear el servicio API.
     * @return Instancia de [ApiMovie].
     */
    @Provides
    @Singleton
    fun provideApiMovie(retrofit: Retrofit): ApiMovie {
        return retrofit.create(ApiMovie::class.java)
    }
}
