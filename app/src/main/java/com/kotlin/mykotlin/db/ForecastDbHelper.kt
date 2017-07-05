package com.kotlin.mykotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.kotlin.mykotlin.App
import org.jetbrains.anko.db.*

/**
 * Created by Gandi on 2017/6/28.
 */
class ForecastDbHelper(ctx: Context = App.instance) : ManagedSQLiteOpenHelper(ctx, ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {

    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance: ForecastDbHelper by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(CityTable.NAME, true, CityTable.ID to INTEGER + PRIMARY_KEY, CityTable.CITYNAME to TEXT, CityTable.LOCATION to TEXT, CityTable.ISCURCITY to INTEGER)

        db.createTable(CurWeatherTable.NAME, true, CurWeatherTable.ID to INTEGER + PRIMARY_KEY, CurWeatherTable.CURCITY to TEXT, CurWeatherTable.CURTEMP to TEXT,
                CurWeatherTable.CONDITIONS to TEXT, CurWeatherTable.DATE to TEXT, CurWeatherTable.HIGH to TEXT, CurWeatherTable.LOW to TEXT)

        db.createTable(WeatherDetailsTable.NAME, true, WeatherDetailsTable.ID to INTEGER, WeatherDetailsTable.SUNRISE to TEXT, WeatherDetailsTable.SUNSET to TEXT,
                WeatherDetailsTable.POP to TEXT, WeatherDetailsTable.HUMIDITY to TEXT, WeatherDetailsTable.WINDDIR to TEXT, WeatherDetailsTable.AVEWIND to TEXT,
                WeatherDetailsTable.FEELSLIKE to TEXT, WeatherDetailsTable.PRESSURE to TEXT, WeatherDetailsTable.VISIBILITY to TEXT, WeatherDetailsTable.UV to TEXT)

        db.createTable(DayForecastWeatherTable.NAME, true, DayForecastWeatherTable.ID to INTEGER, DayForecastWeatherTable.DATE to TEXT, DayForecastWeatherTable.CONDITIONS to TEXT,
                DayForecastWeatherTable.HIGH to TEXT, DayForecastWeatherTable.LOW to TEXT, DayForecastWeatherTable.ICONURL to TEXT, DayForecastWeatherTable.POP to TEXT)

        db.createTable(HourlyForecastWeatherTable.NAME, true, HourlyForecastWeatherTable.ID to INTEGER, HourlyForecastWeatherTable.DATE to TEXT, HourlyForecastWeatherTable.CONDITIONS to TEXT,
                HourlyForecastWeatherTable.TEMP to TEXT, HourlyForecastWeatherTable.ICONURL to TEXT, HourlyForecastWeatherTable.POP to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(CityTable.NAME, true)
        db.dropTable(CurWeatherTable.NAME, true)
        db.dropTable(WeatherDetailsTable.NAME, true)
        db.dropTable(DayForecastWeatherTable.NAME, true)
        db.dropTable(HourlyForecastWeatherTable.NAME, true)
        onCreate(db)
    }

}