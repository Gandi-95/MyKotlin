package com.kotlin.mykotlin.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_weather.*
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.adapter.HourlyForecastAdapter
import com.kotlin.mykotlin.model.HourlyForecastWeather
import com.kotlin.mykotlin.model.WeatherData
import com.kotlin.mykotlin.urlRequest.WeatherUrlRequest
import kotlinx.android.synthetic.main.content_weather.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.properties.Delegates
import android.support.v7.widget.LinearLayoutManager
import kotlin.collections.ArrayList


class WeatherActivity : AppCompatActivity() {

//    http://maven.oschina.NET/content/groups/public?

    var hourlyForecastList: List<HourlyForecastWeather> by Delegates.notNull()
    var hourlyForecastAdapter: HourlyForecastAdapter by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)

        init()
        initOnClick()

    }

    fun init() {
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rlv_hourly_forecast.layoutManager = linearLayoutManager

        hourlyForecastList = ArrayList<HourlyForecastWeather>()
//        if (hourlyForecastList.size>0){
//            hourlyForecastAdapter = HourlyForecastAdapter(hourlyForecastList)
//            rlv_hourly_forecast.adapter = hourlyForecastAdapter
//        }

    }

    fun initOnClick() {
        OnRefreshClick()

        app_bar.addOnOffsetChangedListener {
            _, verticalOffset ->
            if (verticalOffset >= 0) {
                swipeRefresh.setEnabled(true);
            } else {
                swipeRefresh.setEnabled(false);
            }
        }

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    fun OnRefreshClick() {
        swipeRefresh.setOnRefreshListener {
            doAsync {
                val json = WeatherUrlRequest("深圳").execute()
                uiThread {
                    RefreshUi(json)
                }
            }
        }
    }

    fun RefreshUi(weatherData: WeatherData) {
        tv_weather.text = weatherData.toString()

        with(weatherData.curWeather) {
            toolbar_layout.title = curCity
            tv_cur_temp.text = curTemp + "°"
            tv_cur_conditions.text = conditions
        }
        hourlyForecastList = weatherData.hourlyForecastWeather
        hourlyForecastAdapter = HourlyForecastAdapter(hourlyForecastList)
        rlv_hourly_forecast.adapter = hourlyForecastAdapter


        if (swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);//关闭刷新动画
        }
    }

}
