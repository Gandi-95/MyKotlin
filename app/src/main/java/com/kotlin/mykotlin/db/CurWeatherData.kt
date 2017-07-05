package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/29.
 */
class CurWeatherData(val map: MutableMap<String, Any?>) {

    var curCity: String by map
    var curTemp: String by map
    var conditions: String by map
    var date: String by map
    var high: String by map
    var low: String by map

    constructor(curCity: String, curTemp: String, conditions: String, date: String, high: String, low: String) : this(HashMap()) {
        this.curCity = curCity
        this.curTemp = curTemp
        this.conditions = conditions
        this.date = date
        this.high = high
        this.low = low
    }
}