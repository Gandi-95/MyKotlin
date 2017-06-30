package com.kotlin.mykotlin.db

import com.kotlin.mykotlin.model.*

/**
 * Created by Gandi on 2017/6/29.
 */
class WeatherDBDataMapper() {

    fun convertFromDomain(weatherData: WeatherData, cityId: Long) = with(weatherData) {
        val curWeather = convertFromCurWeather(curWeather, cityId)
        val weatherDetails = converFromWeatherDetails(weatherDetails)
        val dayForecastWeather = dayforecastWeather.map { convertFromDayForecastWeather(it) }
        val hourlyForecastWeather = hourlyForecastWeather.map { convertFromHourlyForecastWeather(it) }
        CityWeatherData(curWeather, weatherDetails, dayForecastWeather, hourlyForecastWeather)
    }

    fun convertFromCurWeather(curWeather: CurWeather, cityId: Long) = with(curWeather) {
        CurWeatherData(cityId, curCity, curTemp, conditions, date, high, low)
    }

    fun converFromWeatherDetails(weatherDetails: WeatherDetails) = with(weatherDetails) {
        WeatherDetailsData(sunrise, sunset, pop, humidity, wind_dir, avewind, feelslike, pressure, visibility, UV)
    }

    fun convertFromDayForecastWeather(dayForecastWeather: DayForecastWeather) = with(dayForecastWeather) {
        DayForecastWeatherData(date, conditions, high.toString(), low.toString(), icon_url, pop)
    }

    fun convertFromHourlyForecastWeather(hourlyForecastWeather: HourlyForecastWeather) = with(hourlyForecastWeather){
        HourlyForecastWeatherData(date,conditions,temp,icon_url,pop)
    }


}