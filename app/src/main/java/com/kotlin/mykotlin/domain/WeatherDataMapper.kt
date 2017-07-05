package com.kotlin.mykotlin.domain

import com.kotlin.mykotlin.data.*
import com.kotlin.mykotlin.model.*

/**
 * Created by Gandi on 2017/6/23.
 */
class WeatherDataMapper() {

    fun convertFromDataModel(weatherRequest: WeatherRequest): WeatherData {
        return WeatherData(convertCurWeatherToDomain(weatherRequest.current_observation, weatherRequest.forecast.simpleforecast.forecastday[0]),
                convertWeatherDetailsToDomain(weatherRequest.current_observation, weatherRequest.forecast.simpleforecast.forecastday[0], weatherRequest.sun_phase),
                convertForecastListToDomain(weatherRequest.forecast.simpleforecast), convertHourlyForecastListToDomain(weatherRequest.hourly_forecast))
    }

    private fun convertCurWeatherToDomain(currentObservation: CurrentObservation, simpleForecastday: SimpleForecastday): CurWeather {
        return CurWeather(currentObservation.display_location.city, currentObservation.temp_c, currentObservation.weather, simpleForecastday.date.weekday,
                simpleForecastday.high.celsius.toString(), simpleForecastday.low.celsius.toString())
    }

    private fun convertWeatherDetailsToDomain(currentObservation: CurrentObservation, simpleForecastday: SimpleForecastday, sunPhase: SunPhase): WeatherDetails {
        return WeatherDetails(sunPhase.sunrise.hour + ":" + sunPhase.sunrise.minute, sunPhase.sunset.hour + ":" + sunPhase.sunset.minute, simpleForecastday.pop.toString(),
                currentObservation.relative_humidity, simpleForecastday.avewind.dir, simpleForecastday.avewind.mph.toString(), currentObservation.heat_index_c.toString(),
                currentObservation.precip_today_metric, currentObservation.pressure_mb, currentObservation.visibility_km, currentObservation.UV.toString())
    }

    private fun convertForecastListToDomain(simpleForecast: SimpleForecast): List<DayForecastWeather> {
        return simpleForecast.forecastday.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(simpleForecastDay: SimpleForecastday): DayForecastWeather {
        return DayForecastWeather(simpleForecastDay.date.weekday, simpleForecastDay.conditions, simpleForecastDay.high.celsius.toString(), simpleForecastDay.low.celsius.toString(),
                simpleForecastDay.icon_url, simpleForecastDay.pop.toString())
    }

    private fun convertHourlyForecastListToDomain(list: List<HourlyForecast>): List<HourlyForecastWeather> {
        return list.map { convertHourlyForecastItemToDomain(it) }
    }

    private fun convertHourlyForecastItemToDomain(hourlyForecast: HourlyForecast): HourlyForecastWeather {
        return HourlyForecastWeather(hourlyForecast.FCTTIME.hour, hourlyForecast.condition, hourlyForecast.temp.metric, hourlyForecast.icon_url, hourlyForecast.pop.toString())
    }


    fun convertCityListToDomain(cityPosition: CityPosition): List<City> {
        return cityPosition.geocodes.map { convertCityItemToDomain(it) }
    }

    private fun convertCityItemToDomain(geocodes: Geocodes): City {
        val locationSplit = geocodes.location.split(",")
        val location = locationSplit[1] + "," + locationSplit[0]
        return City(1, geocodes.city, location, 0)
    }
}