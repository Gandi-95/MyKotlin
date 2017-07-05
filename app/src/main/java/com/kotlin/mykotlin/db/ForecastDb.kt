package com.kotlin.mykotlin.db

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.kotlin.mykotlin.model.City
import com.kotlin.mykotlin.model.DayForecastWeather
import com.kotlin.mykotlin.model.HourlyForecastWeather
import com.kotlin.mykotlin.model.WeatherData
import org.jetbrains.anko.db.*

/**
 * Created by Gandi on 2017/6/30.
 */
class ForecastDb(val forecastdbHelper: ForecastDbHelper = ForecastDbHelper.instance, val weatherDbMapper: WeatherDbMapper = WeatherDbMapper()) {

    fun saveWeatherData(weatherData: WeatherData) = forecastdbHelper.use {

        clear(CurWeatherTable.NAME)
        clear(WeatherDetailsTable.NAME)
        clear(DayForecastWeatherTable.NAME)
        clear(HourlyForecastWeatherTable.NAME)

        val cityWeatherData = weatherDbMapper.convertFromDomain(weatherData)
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


    fun getForecast() = forecastdbHelper.use {
        val curWeatherData = select(CurWeatherTable.NAME).parseOpt { CurWeatherData(HashMap(it)) }
        val weatherDetailsData = select(WeatherDetailsTable.NAME).parseOpt { WeatherDetailsData(HashMap(it)) }
        val dayForecastWeatherData = select(DayForecastWeatherTable.NAME).parseList { DayForecastWeatherData(HashMap(it)) }
        val hourlyForecastWeatherData = select(HourlyForecastWeatherTable.NAME).parseList { HourlyForecastWeatherData(HashMap(it)) }


        if (curWeatherData != null && weatherDetailsData != null && dayForecastWeatherData != null && hourlyForecastWeatherData != null) {
            val curWeather = weatherDbMapper.convertToCurWeather(curWeatherData!!)
            val weatherDetails = weatherDbMapper.convertToWeatherDetails(weatherDetailsData!!)
            val dayForecasetWeather = dayForecastWeatherData.map { weatherDbMapper.converToDayForecastWeather(it) }
            val hourlyForecasetWeather = hourlyForecastWeatherData.map { weatherDbMapper.converToHourlyForecastWeather(it) }
            WeatherData(curWeather!!, weatherDetails!!, dayForecasetWeather as List<DayForecastWeather>, hourlyForecasetWeather as List<HourlyForecastWeather>)
        } else {
            null
        }
    }

    fun saveCity(city: City) = forecastdbHelper.use {
        insert(CityTable.NAME, Pair(CityTable.CITYNAME, city.cityName), Pair(CityTable.LOCATION, city.location), Pair(CityTable.ISCURCITY, city.isCurCity))
    }

    fun getCitys() = forecastdbHelper.use {
        //            val rowParser = classParser<City>()
        val cityData = select(CityTable.NAME).parseList { CityData(HashMap(it)) }
        if (cityData != null) cityData.map { weatherDbMapper.converToCity(it) } else null
    }

    fun updataCity(id: Long, isCurCity: Int) = forecastdbHelper.use {
        val cv = ContentValues()
        cv.put(CityTable.ISCURCITY, isCurCity)
        update(CityTable.NAME, cv, "${CityTable.ID} =?", arrayOf(id.toString()))
    }


    fun getCurCity() = forecastdbHelper.use {
        val curCityData = select(CityTable.NAME).whereSimple("${CityTable.ISCURCITY} = 1").parseOpt { CityData(HashMap(it)) }
        if (curCityData != null) weatherDbMapper.converToCity(curCityData) else null
    }

    fun deletCity(_id: Long) = forecastdbHelper.use {
        delete(CityTable.NAME, "_id = $_id")
    }

}


private fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any>) -> T): T? = parseOpt(object : MapRowParser<T> {
    override fun parseRow(columns: Map<String, Any?>): T = parser(columns as Map<String, Any>)

})

fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any>) -> T): List<T> = parseList(object : MapRowParser<T> {
    override fun parseRow(columns: Map<String, Any?>): T = parser(columns as Map<String, Any>)
})


fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}

fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<Pair<K, V>> = map({ Pair(it.key, it.value!!) }).toTypedArray()

