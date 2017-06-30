package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/30.
 */
class DayForecastWeatherData(val map: MutableMap<String, Any?>){

    var date: String by map
    var conditions: String by map
    var high: String by map
    var low: String by map
    var icon_url: String by map
    var pop: String by map

    constructor(date: String, conditions: String, high: String, low: String, icon_url: String, pop: String) : this(HashMap()) {
        this.date = date
        this.conditions = conditions
        this.high = high
        this.low = low
        this.icon_url = icon_url
        this.pop = pop
    }
}