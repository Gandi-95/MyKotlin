package com.kotlin.mykotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.model.HourlyForecastWeather
import org.jetbrains.anko.find


/**
 * Created by Gandi on 2017/6/23.
 */
class HourlyForecastAdapter(val hourlyForecastWeather: List<HourlyForecastWeather>) : RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_hourly_forecast, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindForecast(hourlyForecastWeather[p1])
    }


    override fun getItemCount(): Int = if (hourlyForecastWeather.size > 0) hourlyForecastWeather.size else 0


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tv_hourly:TextView
        private val iv_hourly_icon:ImageView
        private val tv_hourly_temp:TextView


        init {
            tv_hourly = view.find(R.id.tv_hourly)
            iv_hourly_icon = view.find(R.id.iv_hourly_icon)
            tv_hourly_temp = view.find(R.id.tv_hourly_temp)
        }

        fun bindForecast(hourlyForecastWeather: HourlyForecastWeather){
            with(hourlyForecastWeather){
                tv_hourly.text = date
                tv_hourly_temp.text = temp
                Glide.with(itemView.context).load(icon_url).into(iv_hourly_icon)
            }
        }
    }

}