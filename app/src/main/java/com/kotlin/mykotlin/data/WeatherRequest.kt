package com.kotlin.mykotlin.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Gandi on 2017/6/21.
 */
data class WeatherRequest(val response: Response, val current_observation: CurrentObservation, val forecast: Forecast, val hourly_forecast: List<HourlyForecast>,
                          val moon_phase: MoonPhase, val sun_phase: SunPhase)

data class Response(val version: String, val termsofService: String, val features: Features)
data class Features(val conditions: String, val forecast: String, val hourly: String)


//------------------------------------------当前温度-------------------------------------------------------
data class CurrentObservation(val image: Image, val display_location: DisplayLocation, val observation_location: ObservationLocation, val estimated: Any,
                              val station_id: String, val observation_time: String, val observation_time_rfc822: String, val observation_epoch: String,
                              val local_time_rfc822: String, val local_epoch: String, val local_tz_short: String, val local_tz_long: String, val local_tz_offset: String,
                              val weather: String, val temperature_string: String, val temp_f: String, val temp_c: String, val relative_humidity: String,
                              val wind_string: String, val wind_dir: String, val wind_degrees: String, val wind_mph: String, val wind_gust_mph: String, val wind_kph: String,
                              val wind_gust_kph: String, val pressure_mb: String, val pressure_in: String, val pressure_trend: String, val dewpoString_string: String,
                              val dewpoString_f: String, val dewpoString_c: String, val heat_index_string: String, val heat_index_f: String, val heat_index_c: String, val windchill_string: String,
                              val windchill_f: String, val windchill_c: String, val feelslike_string: String, val feelslike_f: String, val feelslike_c: String,
                              val visibility_mi: String, val visibility_km: String, val solarradiation: String, val UV: String, val precip_1hr_string: String,
                              val precip_1hr_in: String, val precip_1hr_metric: String, val precip_today_string: String, val precip_today_in: String, val precip_today_metric: String,
                              val icon: String, val icon_url: String, val forecast_url: String, val history_url: String, val ob_url: String, val nowcast: String)

data class Image(val url: String, val title: String, val link: String)
data class DisplayLocation(val full: String, val city: String, val state: String, val state_name: String, val country: String, val country_iso3166: String,
                           val zip: String, val magic: String, val wmo: String, val latitude: String, val longitude: String, val elevation: String)

data class ObservationLocation(val full: String, val city: String, val state: String, val country: String, val country_iso3166: String, val latitude: String,
                               val longitude: String, val elevation: String)


//------------------------------------------预测温度-------------------------------------------------------
data class Forecast(val txt_forecast: TxtForecast, val simpleforecast: SimpleForecast)

data class TxtForecast(val date: String, val forecastday: List<Forecastday>)
data class Forecastday(val period: String, val icon: String, val icon_url: String, val title: String, val fcttext: String, val fcttext_metric: String, val pop: String)

data class SimpleForecast(val forecastday: List<SimpleForecastday>)
data class SimpleForecastday(val date: Date, val period: String, val high: Unit, val low: Unit, val conditions: String, val icon: String, val icon_url: String, val skyicon: String,
                             val pop: String, val qpf_allday: Qpf, val qpf_day: Qpf, val qpf_night: Qpf, val snow_allday: Qpf, val snow_day: Qpf, val snow_night: Qpf,
                             val maxwind: Wind, val avewind: Wind, val avehumidity: String, val maxhumidity: String, val minhumidity: String)

data class Date(val epoch: String, val pretty: String, val day: String, val month: String, val year: String, val yday: String, val hour: String, val min: String, val sec: String, val isdst: String,
                val monthname: String, val monthname_short: String, val weekday_short: String, val weekday: String, val ampm: String, val tz_short: String, val tz_long: String)

data class Unit(val fahrenheit: String, val celsius: String)
data class Qpf(@SerializedName("in") val inn: String, val mm: String)
data class Wind(val mph: String, val kph: String, val dir: String, val degrees: String)


//------------------------------------------小时温度-------------------------------------------------------
data class HourlyForecast(val FCTTIME: FCTTIME, val temp: BrOrAm, val dewpoString: BrOrAm, val condition: String, val icon: String, val icon_url: String, val fctcode: String,
                          val sky: String, val wspd: BrOrAm, val wdir: Wdir, val wx: String, val uvi: String, val humidity: String, val windchill: BrOrAm, val heatindex: BrOrAm,
                          val feelslike: BrOrAm, val qpf: BrOrAm, val snow: BrOrAm, val pop: String, val mslp: BrOrAm)

data class FCTTIME(val hour: String, val hour_padded: String, val min: String, val min_unpadded: String, val sec: String, val year: String, val mon: String,
                   val mon_padded: String, val mon_abbrev: String, val mday: String, val mday_padded: String, val yday: String, val isdst: String, val epoch: String,
                   val pretty: String, val civil: String, val month_name: String, val month_name_abbrev: String, val weekday_name: String, val weekday_name_night: String,
                   val weekday_name_abbrev: String, val weekday_name_unlang: String, val weekday_name_night_unlang: String, val ampm: String, val tz: String, val age: String, val UTCDATE: String)

data class BrOrAm(val english: String, val metric: String)
data class Wdir(val dir: String, val degrees: String)


//------------------------------------------日出日落-------------------------------------------------------
data class MoonPhase(val percentIlluminated: String, val ageOfMoon: String, val phaseofMoon: String, val hemisphere: String, val current_time: Time, val sunrise: Time, val sunset: Time,
                     val moonrise: Time, val moonset: Time)

data class Time(val hour: String, val minute: String)

data class SunPhase(val sunrise: Time, val sunset: Time)


//------------------------------------------经纬度-------------------------------------------------------
data class CityPosition(val status: String, val info: String, val infocode: String, val count: String, val geocodes: List<Geocodes>)

data class Geocodes(val formatted_address: String, val province: String, val citycode: String, val city: String, val district: Any, val township: Any, val neighborhood: Any, val building: Any,
                    val adcode: String, val street: Any, val number: Any, val location: String, val level: String)












