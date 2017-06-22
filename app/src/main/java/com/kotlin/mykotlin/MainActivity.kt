package com.kotlin.mykotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlin.mykotlin.activity.CourierActivity
import com.kotlin.mykotlin.activity.WeatherActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=关键字&bk_length=600


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }


    fun init(){
        courier.setOnClickListener(this)
        weather.setOnClickListener(this)
    }


    override fun onClick(view: View) {

        when (view.id) {
            R.id.courier -> startActivity<CourierActivity>()
            R.id.weather -> startActivity<WeatherActivity>()
        }
    }
}
