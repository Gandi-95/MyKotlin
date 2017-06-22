package com.kotlin.mykotlin.urlRequest

import com.google.gson.Gson
import com.kotlin.mykotlin.data.WeatherRequest
import java.net.URL

/**
 * Created by Gandi on 2017/6/20.
 */
class WeatherUrlRequest(val city: String) : Command<WeatherRequest> {
    override fun execute(): WeatherRequest {
        val WeatherUrl = "http://api.wunderground.com/api/405aa8a1f8fa74be/conditions/forecast10day/hourly/astronomy/lang:CN/q/$city.json"
        val WeatherJson = URL(WeatherUrl).readText()
        return Gson().fromJson(WeatherJson, WeatherRequest::class.java)
    }

}