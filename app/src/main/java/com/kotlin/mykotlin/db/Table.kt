package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/28.
 */
object CityTable{
    val NAME = "City"
    val ID = "_id"
    val CITYNAME = "cityName"
    val LOCATION = "location"
    val ISCURCITY = "isCurCity"
}

object CurWeatherTable{
    val NAME = "CurWeather"
    val ID = "_id"
    val CURCITY = "curCity"
    val CURTEMP = "curTemp"
    val CONDITIONS = "conditions"
    val DATE = "date"
    val HIGH = "high"
    val LOW = "low"
}

object WeatherDetailsTable{
    val NAME = "WeatherDetails"
    val ID = "_id"
    val SUNRISE = "sunrise"
    val SUNSET = "sunset"
    val POP = "pop"
    val HUMIDITY = "humidity"
    val WINDDIR = "wind_dir"
    val AVEWIND = "avewind"
    val FEELSLIKE = "feelslike"
    val PRESSURE = "pressure"
    val VISIBILITY = "visibility"
    val UV = "uv"
}

object DayForecastWeatherTable{
    val NAME = "DayForecastWeather"
    val ID = "_id"
    val DATE = "date"
    val CONDITIONS = "conditions"
    val HIGH = "high"
    val LOW = "low"
    val ICONURL = "icon_url"
    val POP = "pop"
}

object HourlyForecastWeatherTable{
    val NAME = "HourlyForecastWeather"
    val ID = "_id"
    val DATE = "date"
    val CONDITIONS = "conditions"
    val TEMP = "temp"
    val ICONURL = "icon_url"
    val POP = "pop"
}

