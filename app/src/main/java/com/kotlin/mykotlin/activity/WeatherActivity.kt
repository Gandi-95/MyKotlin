package com.kotlin.mykotlin.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_weather.*
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.adapter.HourlyForecastAdapter
import com.kotlin.mykotlin.model.HourlyForecastWeather
import com.kotlin.mykotlin.urlRequest.WeatherUrlRequest
import kotlinx.android.synthetic.main.content_weather.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.kotlin.mykotlin.adapter.DayForecastAdapter
import com.kotlin.mykotlin.db.ForecastDb
import com.kotlin.mykotlin.model.City
import com.kotlin.mykotlin.model.DayForecastWeather
import com.kotlin.mykotlin.model.WeatherData
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import kotlin.collections.ArrayList


class WeatherActivity : AppCompatActivity() {

    var HANDLER_REFRESH_CLOSE = 1

    var hourlyForecastList: MutableList<HourlyForecastWeather> by Delegates.notNull()
    var hourlyForecastAdapter: HourlyForecastAdapter by Delegates.notNull()
    var dayForecastList: MutableList<DayForecastWeather> by Delegates.notNull()
    var dayForecastAdapter: DayForecastAdapter by Delegates.notNull()

    var curCity: City? = null


    var mHandler: Handler = object : Handler() {

        override fun handleMessage(msg: Message?) {
            when (msg!!.what) {
                HANDLER_REFRESH_CLOSE -> if (swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);//关闭刷新动画
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)
//        toolbar.setNavigationIcon(R.mipmap.icon_back)
//        toolbar.setNavigationOnClickListener { finish() }

        init()
        initOnClick()

        curCity = ForecastDb().getCurCity()
        val weatherData = ForecastDb().getForecast()
        if (weatherData != null) RefreshUi(weatherData)


    }

    fun init() {
        rlv_hourly_forecast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        hourlyForecastList = ArrayList<HourlyForecastWeather>()
        hourlyForecastAdapter = HourlyForecastAdapter(hourlyForecastList)
        rlv_hourly_forecast.adapter = hourlyForecastAdapter

        rlv_day_forecast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dayForecastList = ArrayList<DayForecastWeather>()
        dayForecastAdapter = DayForecastAdapter(dayForecastList)
        rlv_day_forecast.adapter = dayForecastAdapter

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
            val intent = Intent(this, WeatherCityActivity::class.java)
            startActivityForResult(intent, 1)
        }

        iv_back.setOnClickListener { finish() }

    }


    fun OnRefreshClick() {
        swipeRefresh.setOnRefreshListener {
            if (curCity!=null) LoadWeather(curCity!!.location) else toast("请添加城市")

            mHandler.sendEmptyMessageDelayed(HANDLER_REFRESH_CLOSE, 5000)
        }
    }


    fun LoadWeather(location: String) {
        doAsync {
            val weatherData = WeatherUrlRequest(location).execute()
            ForecastDb().saveWeatherData(weatherData)
            uiThread {
                RefreshUi(weatherData)
            }
        }
    }


    fun RefreshUi(weatherData: WeatherData) {
        if (curCity != null) toolbar_layout.title = curCity!!.cityName else toolbar_layout.title = weatherData.curWeather.curCity

        with(weatherData.curWeather) {
            tv_cur_temp.text = curTemp + resources.getString(R.string.unit)
            tv_cur_conditions.text = conditions
            tv_today_time.text = date + "  今天"
            tv_today_temp.text = high + "  " + low
        }

        with(weatherData.weatherDetails) {
            tv_sunrise.text = sunrise
            tv_sunset.text = sunset
            tv_pop.text = pop
            tv_humidity.text = humidity
            tv_wind.text = wind_dir + " " + avewind
            tv_feelslike.text = feelslike
            tv_precip.text = precip
            tv_pressure.text = pressure
            tv_visibility.text = visibility
            tv_uv.text = UV

        }

        hourlyForecastList.clear()
        hourlyForecastList.addAll(weatherData.hourlyForecastWeather)
        hourlyForecastAdapter.notifyDataSetChanged()

        dayForecastList.clear()
        dayForecastList.addAll(weatherData.dayforecastWeather)
        dayForecastAdapter.notifyDataSetChanged()

        //关闭刷新动画
        if (swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == 1) {
            curCity = ForecastDb().getCurCity()
            LoadWeather(curCity!!.location)
            mHandler.sendEmptyMessageDelayed(HANDLER_REFRESH_CLOSE, 5000)
        }
    }

}
