package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/30.
 */
class WeatherDetailsData(val map: MutableMap<String, Any?>){

    var sunrise: String by map
    var sunset: String by map
    var pop: String by map
    var humidity: String by map
    var wind_dir: String by map
    var avewind: String by map
    var feelslike: String by map
    var pressure: String by map
    var visibility: String by map
    var uv: String by map

    constructor(sunrise: String, sunset: String, pop: String, humidity: String, wind_dir: String, avewind: String, feelslike: String, pressure: String, visibility: String, uv: String) : this(HashMap()) {
        this.sunrise = sunrise
        this.sunset = sunset
        this.pop = pop
        this.humidity = humidity
        this.wind_dir = wind_dir
        this.avewind = avewind
        this.feelslike = feelslike
        this.pressure = pressure
        this.visibility = visibility
        this.uv = uv
    }
}