package com.kotlin.mykotlin.model

/**
 * Created by Gandi on 2017/6/22.
 */
data class WeatherData(val curWeather: CurWeather, val weatherDetails: WeatherDetails, val dayforecastWeather: List<DayForecastWeather>, val hourlyForecastWeather: List<HourlyForecastWeather>)

data class CurWeather(val curCity: String, val curTemp: String, val conditions: String, val date: String, val tempRange: TempRange)

/**
 * @sunrise 日出  @sunset 日落  @pop 降雨概率  @humidity 湿度  @wind_dir 风向  @avewind 风速  @feelslike 热指数  @precip 降雨量(毫米)  @pressure 气压(百帕)  @visibility 可见度(公里)  @UV 紫外线
 */
data class WeatherDetails(val sunrise: String, val sunset: String, val pop: String, val humidity: String, val wind_dir: String, val avewind: String, val feelslike: String, val precip: String,
                          val pressure: String, val visibility: String, val UV: String)

data class TempRange(val high: Int, val low: Int)

data class DayForecastWeather(val date: String, val conditions: String, val tempRange: TempRange, val icon_url: String, val pop: String)

data class HourlyForecastWeather(val date: String, val conditions: String, val temp: String, val icon_url: String, val pop: String)
