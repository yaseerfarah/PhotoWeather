package com.yasser.photoweather.modules.cameraweather.data.source



import com.yasser.photoweather.BuildConfig
import com.yasser.photoweather.core.network.ApiEndpoints
import com.yasser.photoweather.modules.cameraweather.data.model.WeatherResponse
import javax.inject.Inject

class WeatherApiSource @Inject constructor(
    private val apiEndpoints: ApiEndpoints
) {

    suspend fun getCurrentWeather():WeatherResponse{
        var  weatherResponse:WeatherResponse? = null
        val response= apiEndpoints.getCurrentWeather(
            apiKey = BuildConfig.ApiKey,
            "Cairo"
        )
        if (response.isSuccessful){
            weatherResponse=response.body()
        }else{
            val message = "Something went wrong"

           throw (Throwable(message))
        }
        return weatherResponse!!
    }




}