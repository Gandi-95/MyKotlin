package com.kotlin.mykotlin.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Gandi on 2017/6/22.
 */
data class WeatherData(val curWeather: CurWeather, val weatherDetails: WeatherDetails, val dayforecastWeather: List<DayForecastWeather>, val hourlyForecastWeather: List<HourlyForecastWeather>)

data class CurWeather(val curCity: String, val curTemp: String, val conditions: String, val date: String, val high: String, val low: String)

/**
 * @sunrise 日出  @sunset 日落  @pop 降雨概率  @humidity 湿度  @wind_dir 风向  @avewind 风速  @feelslike 热指数  @precip 降雨量(毫米)  @pressure 气压(百帕)  @visibility 可见度(公里)  @UV 紫外线
 */
data class WeatherDetails(val sunrise: String, val sunset: String, val pop: String, val humidity: String, val wind_dir: String, val avewind: String, val feelslike: String, val precip: String,
                          val pressure: String, val visibility: String, val UV: String)

data class DayForecastWeather(val date: String, val conditions: String, val high: String, val low: String, val icon_url: String, val pop: String)

data class HourlyForecastWeather(val date: String, val conditions: String, val temp: String, val icon_url: String, val pop: String)

data class City(val _id: Long, val cityName: String, val location: String,val isCurCity:Int) : Parcelable {

    constructor(source: Parcel) : this(source.readLong(), source.readString(), source.readString(), source.readInt())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(this._id)
        dest.writeString(this.cityName)
        dest.writeString(this.location)
        dest.writeInt(this.isCurCity)
    }

    companion object {

        @JvmField val CREATOR: Parcelable.Creator<City> = object : Parcelable.Creator<City> {
            override fun createFromParcel(source: Parcel): City {
                return City(source)
            }

            override fun newArray(size: Int): Array<City?> {
                return arrayOfNulls(size)
            }
        }
    }
}