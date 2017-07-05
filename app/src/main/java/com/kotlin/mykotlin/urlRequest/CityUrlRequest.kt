package com.kotlin.mykotlin.urlRequest

import com.google.gson.Gson
import com.kotlin.mykotlin.data.CityPosition
import com.kotlin.mykotlin.domain.WeatherDataMapper
import com.kotlin.mykotlin.model.City
import org.json.JSONObject
import java.net.URL

/**
 * Created by Administrator on 2017/7/1 0001.
 */
class CityUrlRequest(val city: String) : Command<List<City>> {

//    http://api.map.baidu.com/geocoder?address=深圳&output=json&key=37492c0ee6f924cb5e934fa08c

    override fun execute(): List<City>? {
        val cityUrl = "http://restapi.amap.com/v3/geocode/geo?key=9a491a13997579f985c98165bb68d127&s=rsv3&address=$city"
        val cityjson = URL(cityUrl).readText()
        val cityList = Gson().fromJson(cityjson, CityPosition::class.java)
        return WeatherDataMapper().convertCityListToDomain(cityList)
    }

}