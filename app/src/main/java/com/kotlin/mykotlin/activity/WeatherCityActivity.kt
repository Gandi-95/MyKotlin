package com.kotlin.mykotlin.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.db.ForecastDb
import com.kotlin.mykotlin.model.City
import kotlinx.android.synthetic.main.activity_weather_city.*
import kotlin.properties.Delegates
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.baoyz.swipemenulistview.SwipeMenuItem
import com.baoyz.swipemenulistview.SwipeMenuCreator
import com.baoyz.swipemenulistview.SwipeMenuListView
import com.baoyz.swipemenulistview.SwipeMenu
import org.jetbrains.anko.toast


class WeatherCityActivity : AppCompatActivity() {

    var cityNameList: MutableList<String> by Delegates.notNull()
    var cityAdapter: ArrayAdapter<String> by Delegates.notNull()
    var cityList: MutableList<City> by Delegates.notNull()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_city)
        init()


        cityNameList = ArrayList<String>()
        cityAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cityNameList)
        lv_city.adapter = cityAdapter

        RefreshCity()


    }

    fun init() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.mipmap.icon_back)
        toolbar.setNavigationOnClickListener { finish() }

        iv_add_city.setOnClickListener {
            val intent = Intent(this, SearchCityActivity::class.java)
            startActivityForResult(intent, 1)
        }

        lv_city.setMenuCreator(creator)
        lv_city.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT)


        lv_city.setOnMenuItemClickListener(object : SwipeMenuListView.OnMenuItemClickListener {
            override fun onMenuItemClick(position: Int, menu: SwipeMenu, index: Int): Boolean {
                when (index) {
                    0 -> {
                        ForecastDb().deletCity(cityList[position]._id)
                        RefreshCity()
                        val curCity = ForecastDb().getCurCity()
                        if (curCity == null && cityList.size>0) {
                            ForecastDb().updataCity(cityList[0]._id, 1)
                        }
                    }
                }
                // false : close the menu; true : not close the menu
                return false
            }
        })

        lv_city.setOnItemClickListener {
            parent, view, position, id ->

            val curCity = ForecastDb().getCurCity()
            if (curCity != null) {
                ForecastDb().updataCity(curCity._id, 0)
            }
            ForecastDb().updataCity(cityList[position]._id, 1)

            val intent = Intent()
            intent.putExtra("city", cityList[position])
            setResult(1, intent)
            finish()
        }
    }


    var creator: SwipeMenuCreator = SwipeMenuCreator { menu ->
        //        // create "open" item
//        val openItem = SwipeMenuItem(
//                applicationContext)
//        // set item background
//        openItem.background = ColorDrawable(Color.rgb(0xC9, 0xC9,
//                0xCE))
//        // set item width
//        openItem.width = dip2px(90f)
//        // set item title
//        openItem.title = "Open"
//        // set item title fontsize
//        openItem.titleSize = 18
//        // set item title font color
//        openItem.titleColor = Color.WHITE
//        // add to menu
//        menu.addMenuItem(openItem)

        // create "delete" item
        val deleteItem = SwipeMenuItem(applicationContext)
        // set item background
        deleteItem.background = ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25))
        // set item width
        deleteItem.width = dip2px(80f)
        // set a icon
//        deleteItem.setIcon(R.mipmap.icon_delete)
        // set item title
        deleteItem.title = "删除"
        deleteItem.titleSize = 18
        deleteItem.titleColor = Color.WHITE
        // add to menu
        menu.addMenuItem(deleteItem)
    }

    /**
     * dp2px
     */
    fun dip2px(dpValue: Float): Int {
        val scale = getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == 1) {
            RefreshCity()
        }
    }


    fun RefreshCity() {
        cityList = ForecastDb().getCitys() as MutableList<City>
        cityNameList.clear()
        if (cityList != null) {
            for (city in cityList) {
                cityNameList.add(city.cityName)
            }
            cityAdapter.notifyDataSetChanged()
        }
    }


}

