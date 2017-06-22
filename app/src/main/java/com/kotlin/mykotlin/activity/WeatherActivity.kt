package com.kotlin.mykotlin.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_weather.*
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.urlRequest.WeatherUrlRequest
import kotlinx.android.synthetic.main.content_weather.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class WeatherActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        setSupportActionBar(toolbar)
        toolbar_layout.title = "深圳"



        swipeRefresh.setOnRefreshListener {
            doAsync {
                val json = WeatherUrlRequest("深圳").execute()
                uiThread {
                    tv_weather.text = json.toString()
                    if (swipeRefresh.isRefreshing()) {
                        //关闭刷新动画
                        swipeRefresh.setRefreshing(false);
                    }
                }
            }
        }

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
}
