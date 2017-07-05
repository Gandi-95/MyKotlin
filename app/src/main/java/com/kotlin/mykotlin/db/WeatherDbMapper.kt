package com.kotlin.mykotlin.db

import com.kotlin.mykotlin.model.*

/**
 * Created by Gandi on 2017/6/29.
 */
class WeatherDbMapper() {

    fun convertFromDomain(weatherData: WeatherData) = with(weatherData) {
        val curWeather = convertFromCurWeather(curWeather)
        val weatherDetails = converFromWeatherDetails(weatherDetails)
        val dayForecastWeather = dayforecastWeather.map { convertFromDayForecastWeather(it) }
        val hourlyForecastWeather = hourlyForecastWeather.map { convertFromHourlyForecastWeather(it) }
        CityWeatherData(curWeather, weatherDetails, dayForecastWeather, hourlyForecastWeather)
    }

    fun convertFromCurWeather(curWeather: CurWeather) = with(curWeather) {
        CurWeatherData(curCity, curTemp, conditions, date, high, low)
    }

    fun converFromWeatherDetails(weatherDetails: WeatherDetails) = with(weatherDetails) {
        WeatherDetailsData(sunrise, sunset, pop, humidity, wind_dir, avewind, feelslike, pressure, visibility, UV)
    }

    fun convertFromDayForecastWeather(dayForecastWeather: DayForecastWeather) = with(dayForecastWeather) {
        DayForecastWeatherData(date, conditions, high, low, icon_url, pop)
    }

    fun convertFromHourlyForecastWeather(hourlyForecastWeather: HourlyForecastWeather) = with(hourlyForecastWeather) {
        HourlyForecastWeatherData(date, conditions, temp, icon_url, pop)
    }


    fun convertToCurWeather(curWeatherData: CurWeatherData) = with(curWeatherData) {
        if (curWeatherData != null) CurWeather(curCity, curTemp, conditions, date, high, low) else null
    }

    fun convertToWeatherDetails(weatherDetailsData: WeatherDetailsData) = with(weatherDetailsData) {
        if (weatherDetailsData != null) WeatherDetails(sunrise, sunset, pop, humidity, wind_dir, avewind, feelslike, pressure, pressure, visibility, uv) else null
    }

    fun converToDayForecastWeather(dayForecastWeatherData: DayForecastWeatherData) = with(dayForecastWeatherData) {
        if (dayForecastWeatherData != null) DayForecastWeather(date, conditions, high, low, icon_url, pop) else null
    }

    fun converToHourlyForecastWeather(hourlyForecastWeatherData: HourlyForecastWeatherData) = with(hourlyForecastWeatherData) {
        if (hourlyForecastWeatherData != null) HourlyForecastWeather(date, conditions, temp, icon_url, pop) else null
    }


    fun converToCity(cityData: CityData) = with(cityData) {
        City(_id, cityName, location, isCurCity)
    }

}