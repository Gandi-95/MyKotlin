package com.kotlin.mykotlin.db

/**
 * Created by Administrator on 2017/7/3 0003.
 */
class CityData(val map: MutableMap<String, Any?>) {

    var _id: Long by map
    var cityName: String by map
    var location: String by map
    var isCurCity: Int by map

    constructor(_id: Long, cityName: String, location: String, isCurCity: Int) : this(HashMap()) {
        this._id = _id
        this.cityName = cityName
        this.location = location
        this.isCurCity = isCurCity

    }
}