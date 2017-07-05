package com.kotlin.mykotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.db.ForecastDb
import com.kotlin.mykotlin.model.City
import com.kotlin.mykotlin.urlRequest.CityUrlRequest
import kotlinx.android.synthetic.main.activity_search_city.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates


class SearchCityActivity : AppCompatActivity() {

    var cityList: MutableList<String> by Delegates.notNull()
    var Citys: MutableList<City> by Delegates.notNull()
    var cityAdapter: ArrayAdapter<String> by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)

        cityList = ArrayList<String>()
        cityAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList)
        lv_search_city.adapter = cityAdapter

        sv_city.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!TextUtils.isEmpty(newText)){
                    doAsync {
                        Citys= CityUrlRequest(newText!!).execute() as MutableList<City>
                        uiThread {
                            if (Citys!=null) {
                                cityList.clear()
                                for (city in Citys){
                                    cityList.add(city.cityName)
                                }
                                cityAdapter.notifyDataSetChanged()
                            }
                        }
                    }

                }else{
                    cityList.clear()
                    cityAdapter.notifyDataSetChanged()
                }
                return true;
            }

        })

        lv_search_city.setOnItemClickListener{
            _,_,position,_ ->
            ForecastDb().saveCity(Citys[position])
            setResult(1)
            finish()
        }

        tv_cancel.setOnClickListener { finish() }
    }



}




