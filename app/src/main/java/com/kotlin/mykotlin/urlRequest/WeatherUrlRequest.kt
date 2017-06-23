package com.kotlin.mykotlin.urlRequest

import com.google.gson.Gson
import com.kotlin.mykotlin.data.WeatherRequest
import com.kotlin.mykotlin.domain.WeatherDataMapper
import com.kotlin.mykotlin.model.WeatherData
import java.net.URL

/**
 * Created by Gandi on 2017/6/20.
 */
class WeatherUrlRequest(val city: String) : Command<WeatherData> {
    override fun execute(): WeatherData {
        val WeatherUrl = "http://api.wunderground.com/api/405aa8a1f8fa74be/conditions/forecast10day/hourly/astronomy/lang:CN/q/$city.json"
        val WeatherJson = URL(WeatherUrl).readText()
        val weatherRequest = Gson().fromJson(WeatherJson, WeatherRequest::class.java)

        return WeatherDataMapper().convertFromDataModel(weatherRequest)
    }

}