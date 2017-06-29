package com.kotlin.mykotlin.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Gandi on 2017/6/21.
 */
data class WeatherRequest(val response: Response, val current_observation: CurrentObservation, val forecast: Forecast, val hourly_forecast: List<HourlyForecast>,
                          val moon_phase: MoonPhase, val sun_phase: SunPhase)

data class Response(val version: Double, val termsofService: String, val features: Features)
data class Features(val conditions: Int, val forecast: Int, val hourly: Int)


//------------------------------------------当前温度-------------------------------------------------------
data class CurrentObservation(val image: Image, val display_location: DisplayLocation, val observation_location: ObservationLocation, val estimated: Any,
                              val station_id: String, val observation_time: String, val observation_time_rfc822: String, val observation_epoch: Int,
                              val local_time_rfc822: String, val local_epoch: Int, val local_tz_short: String, val local_tz_long: String, val local_tz_offset: String,
                              val weather: String, val temperature_string: String, val temp_f: String, val temp_c: String, val relative_humidity: String,
                              val wind_string: String, val wind_dir: String, val wind_degrees: Int, val wind_mph: Double, val wind_gust_mph: Double, val wind_kph: Double,
                              val wind_gust_kph: String, val pressure_mb: String, val pressure_in: String, val pressure_trend: String, val dewpoint_string: String,
                              val dewpoint_f: Int, val dewpoint_c: Int, val heat_index_string: String, val heat_index_f: Int, val heat_index_c: Int, val windchill_string: String,
                              val windchill_f: String, val windchill_c: String, val feelslike_string: String, val feelslike_f: Int, val feelslike_c: Int,
                              val visibility_mi: String, val visibility_km: String, val solarradiation: String, val UV: Double, val precip_1hr_string: String,
                              val precip_1hr_in: String, val precip_1hr_metric: String, val precip_today_string: String, val precip_today_in: String, val precip_today_metric: String,
                              val icon: String, val icon_url: String, val forecast_url: String, val history_url: String, val ob_url: String, val nowcast: String)

data class Image(val url: String, val title: String, val link: String)
data class DisplayLocation(val full: String, val city: String, val state: Int, val state_name: String, val country: String, val country_iso3166: String,
                           val zip: String, val magic: Int, val wmo: Int, val latitude: Double, val longitude: Double, val elevation: Double)

data class ObservationLocation(val full: String, val city: String, val state: String, val country: String, val country_iso3166: String, val latitude: Double,
                               val longitude: Double, val elevation: String)


//------------------------------------------预测温度-------------------------------------------------------
data class Forecast(val txt_forecast: TxtForecast, val simpleforecast: SimpleForecast)

data class TxtForecast(val date: String, val forecastday: List<Forecastday>)
data class Forecastday(val period: Int, val icon: String, val icon_url: String, val title: String, val fcttext: String, val fcttext_metric: String, val pop: Int)

data class SimpleForecast(val forecastday: List<SimpleForecastday>)
data class SimpleForecastday(val date: Date, val period: Int, val high: Unit, val low: Unit, val conditions: String, val icon: String, val icon_url: String, val skyicon: String,
                             val pop: Int, val qpf_allday: Qpf, val qpf_day: Qpf, val qpf_night: Qpf, val snow_allday: Qpf, val snow_day: Qpf, val snow_night: Qpf,
                             val maxwind: Wind, val avewind: Wind, val avehumidity: Int, val maxhumidity: Int, val minhumidity: Int)

data class Date(val epoch: Int, val pretty: String, val day: Int, val month: Int, val year: Int, val yday: Int, val hour: Int, val min: Int, val sec: Int, val isdst: Int,
                val monthname: String, val monthname_short: String, val weekday_short: String, val weekday: String, val ampm: String, val tz_short: String, val tz_long: String)

data class Unit(val fahrenheit: Int, val celsius: Int)
data class Qpf(@SerializedName("in") val inn: Double, val mm: Int)
data class Wind(val mph: Int, val kph: Int, val dir: String, val degrees: Int)


//------------------------------------------小时温度-------------------------------------------------------
data class HourlyForecast(val FCTTIME: FCTTIME, val temp: BrOrAm, val dewpoint: BrOrAm, val condition: String, val icon: String, val icon_url: String, val fctcode: Int,
                          val sky: Int, val wspd: BrOrAm, val wdir: Wdir, val wx: String, val uvi: Int, val humidity: Int, val windchill: BrOrAm, val heatindex: BrOrAm,
                          val feelslike: BrOrAm, val qpf: BrOrAm, val snow: BrOrAm, val pop: Int, val mslp: BrOrAm)

data class FCTTIME(val hour: String, val hour_padded: String, val min: String, val min_unpadded: String, val sec: String, val year: String, val mon: String,
                   val mon_padded: String, val mon_abbrev: String, val mday: String, val mday_padded: String, val yday: Int, val isdst: Int, val epoch: Int,
                   val pretty: String, val civil: String, val month_name: String, val month_name_abbrev: String, val weekday_name: String, val weekday_name_night: String,
                   val weekday_name_abbrev: String, val weekday_name_unlang: String, val weekday_name_night_unlang: String, val ampm: String, val tz: String, val age: String, val UTCDATE: String)

data class BrOrAm(val english: String, val metric: String)
data class Wdir(val dir: String, val degrees: Int)


//------------------------------------------日出日落-------------------------------------------------------
data class MoonPhase(val percentIlluminated: Int, val ageOfMoon: Int, val phaseofMoon: String, val hemisphere: String, val current_time: Time, val sunrise: Time, val sunset: Time,
                     val moonrise: Time, val moonset: Time)

data class Time(val hour: String, val minute: String)

data class SunPhase(val sunrise: Time, val sunset: Time)
















