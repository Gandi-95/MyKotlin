package com.kotlin.mykotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kotlin.mykotlin.R
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    http://baike.baidu.com/api/openapi/BaikeLemmaCardApi?scope=103&format=json&appid=379020&bk_key=关键字&bk_length=600


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }


    fun init(){
        kotlinx.android.synthetic.main.activity_main.courier.setOnClickListener(this)
        kotlinx.android.synthetic.main.activity_main.weather.setOnClickListener(this)
    }


    override fun onClick(view: View) {

        when (view.id) {
            R.id.courier -> startActivity<CourierActivity>()
            R.id.weather -> startActivity<WeatherActivity>()
        }
    }
}
