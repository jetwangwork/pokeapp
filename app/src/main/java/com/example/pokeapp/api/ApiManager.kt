package com.example.pokeapp.api

import com.example.pokeapp.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {

    private val json = Json {
        ignoreUnknownKeys = true // 如果 JSON 多了 data class 裡沒有的欄位 → 忽略
        coerceInputValues = true // 如果 JSON 欄位是錯型別或非法值 → 改成預設值
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val pokemonApi: PokemonApiService by lazy {
        retrofit.create(PokemonApiService::class.java)
    }

    val digimonApi: DigimonApiService by lazy {
        retrofit.create(DigimonApiService::class.java)
    }
}
