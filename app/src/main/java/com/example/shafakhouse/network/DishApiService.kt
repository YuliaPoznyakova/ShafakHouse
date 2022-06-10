package com.example.shafakhouse.network

import com.example.shafakhouse.model.Dish
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://shafakhouse-default-rtdb.europe-west1.firebasedatabase.app/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DishApiService {
    @GET("dishes.json")
    suspend fun getPhotos():  List<Dish>
}

object DishApi {
    val retrofitService : DishApiService by lazy {
        retrofit.create(DishApiService::class.java)
    }
}