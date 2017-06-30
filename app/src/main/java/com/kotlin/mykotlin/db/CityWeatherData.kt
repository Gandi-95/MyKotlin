package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/30.
 */
class CityWeatherData(val map: MutableMap<String, Any>, val dayForecastWeather: List<DayForecastWeatherData>, val hourlyForecastWeather: List<HourlyForecastWeatherData>) {

    var curWeather: CurWeatherData by map
    var weatherDetails: WeatherDetailsData by map

    constructor(curWeather: CurWeatherData, weatherDetails: WeatherDetailsData, dayForecastWeather: List<DayForecastWeatherData>, hourlyForecastWeather: List<HourlyForecastWeatherData>) : this(HashMap(), dayForecastWeather, hourlyForecastWeather) {
        this.curWeather = curWeather
        this.weatherDetails = weatherDetails
    }
}