package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/30.
 */
class HourlyForecastWeatherData(val map: MutableMap<String, Any?>){

    var date: String by map
    var conditions: String by map
    var temp: String by map
    var icon_url: String by map
    var pop: String by map

    constructor(date: String, conditions: String, temp: String, icon_url: String, pop: String) : this(HashMap()){
        this.date = date
        this.conditions = conditions
        this.temp = temp
        this.icon_url = icon_url
        this.pop = pop
    }
}