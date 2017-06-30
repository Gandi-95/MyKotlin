package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/29.
 */
class CurWeatherData(val map: MutableMap<String, Any?>) {

    var cityId: Long by map
    var curCity: String by map
    var curTemp: String by map
    var conditions: String by map
    var date: String by map
    var high: Int by map
    var low: Int by map

    constructor(cityId: Long, curCity: String, curTemp: String, conditions: String, date: String, high: Int, low: Int) : this(HashMap()) {
        this.cityId = cityId
        this.curCity = curCity
        this.curTemp = curTemp
        this.conditions = conditions
        this.date = date
        this.high = high
        this.low = low
    }
}