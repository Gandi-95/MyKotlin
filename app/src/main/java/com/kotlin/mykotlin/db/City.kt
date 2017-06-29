package com.kotlin.mykotlin.db

/**
 * Created by Gandi on 2017/6/29.
 */
class City(val map: MutableMap<String, Any>) {

    var cityName: String by map

    constructor(cityName: String) : this(HashMap()){
        this.cityName = cityName
    }
}