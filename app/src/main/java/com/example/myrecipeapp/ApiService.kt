package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
/*
The Retrofit Builder() is responsible for preparing the endpoint and adding the Json Converter.
It then provide create method to gain access to the service methods
 */

    val recipeService = retrofit.create(ApiService::class.java)
// Combining retrofit with ApiService

interface ApiService{

    @GET("categories.php") //It allows us to make an http Request
    /*
    This @ is helping us by adding the necessary code in the background to make the network request,
    send a get request to the specified url and process the response data
     */
    //The other part of this URL will be saved somewhere else as the same url will be used somewhere else also
    suspend fun getCategories(): CategoriesResponse
    /*
    suspend is a part of coroutine API which helps in running many things at a time.
    If there's something which is taking time to load due to so many factors(internet speed,etc), The app
    will freeze, So suspend helps in working that process in background while the other stuff gets loaded
    without making the UI Thread block.
     */
    /*
    Coroutines are a powerful and lightweight framework in kotlin,specially designed for handling
    asynchronous and non-blocking blocking operations. Basically It runs time consuming code and requests
    in the background.
     */
}