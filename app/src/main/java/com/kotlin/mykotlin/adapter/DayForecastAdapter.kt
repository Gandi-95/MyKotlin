package com.kotlin.mykotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.model.DayForecastWeather
import org.jetbrains.anko.find

/**
 * Created by Gandi on 2017/6/27.
 */
class DayForecastAdapter(val dayForecastWeather: MutableList<DayForecastWeather>) : RecyclerView.Adapter<DayForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather_day_forecast, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindForecast(dayForecastWeather[p1])
    }


    override fun getItemCount(): Int = if(dayForecastWeather==null) 0 else dayForecastWeather.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tv_day: TextView
        private val iv_day_icon: ImageView
        private val tv_day_temp: TextView


        init {
            tv_day = view.find(R.id.tv_day)
            iv_day_icon = view.find(R.id.iv_day_icon)
            tv_day_temp = view.find(R.id.tv_day_temp)
        }

        fun bindForecast(dayForecastWeather: DayForecastWeather){
            with(dayForecastWeather){
                tv_day.text = dayForecastWeather.date
                Glide.with(itemView.context).load(dayForecastWeather.icon_url).into(iv_day_icon)
                tv_day_temp.text = dayForecastWeather.high.toString()+"  "+dayForecastWeather.low
            }
        }
    }

}