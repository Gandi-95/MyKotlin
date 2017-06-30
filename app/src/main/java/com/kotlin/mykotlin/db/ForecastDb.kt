package com.kotlin.mykotlin.db

import android.database.sqlite.SQLiteDatabase
import com.kotlin.mykotlin.model.WeatherData
import org.jetbrains.anko.db.*

/**
 * Created by Gandi on 2017/6/30.
 */
class ForecastDb(val forecastdbHelper: ForecastDbHelper = ForecastDbHelper.instance) {

    fun saveWeatherData(weatherData: WeatherData) {
        try {
            forecastdbHelper.use {

                val cityWeatherData = WeatherDBDataMapper().convertFromDomain(weatherData, 1)
                with(cityWeatherData.curWeather) {
                    insert(CurWeatherTable.NAME, *map.toVarargArray())
                }
                with(cityWeatherData.weatherDetails) {
                    insert(WeatherDetailsTable.NAME, *map.toVarargArray())
                }
                cityWeatherData.dayForecastWeather.forEach {
                    insert(DayForecastWeatherTable.NAME, *it.map.toVarargArray())
                }
                cityWeatherData.hourlyForecastWeather.forEach {
                    insert(HourlyForecastWeatherTable.NAME, *it.map.toVarargArray())
                }
            }
        }finally {
            forecastdbHelper.close()
        }
    }
}


fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<Pair<K, V>> = map({ Pair(it.key, it.value!!) }).toTypedArray()

