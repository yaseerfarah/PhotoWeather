package com.yasser.photoweather.core.network



import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoints {



    @GET("current.json")
    suspend fun getCurrentWeather(@Query("key") apiKey:String,@Query("q") currentLocation:String): Response<WeatherResponse>





}