package com.antonio.network.di

import com.antonio.network.LeboncoinNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {
    private const val LEBONCOIN_BASE_URL = "https://static.leboncoin.fr/"
    private const val LEBONCOIN_RETROFIT = "leboncoin_retrofit"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
           // .addInterceptor(HttpLoggingInterceptor().apply {
           //        setLevel(HttpLoggingInterceptor.Level.BODY)
           // })
            .build()
    }

    @Provides
    @Singleton
    @Named(LEBONCOIN_RETROFIT)
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(LEBONCOIN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideLeboncoinNetworkDataSource(@Named(LEBONCOIN_RETROFIT) retrofit: Retrofit): LeboncoinNetworkDataSource {
        return retrofit.create(LeboncoinNetworkDataSource::class.java)
    }
}