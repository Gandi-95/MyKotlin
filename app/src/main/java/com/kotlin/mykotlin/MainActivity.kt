package com.kotlin.mykotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.mykotlin.activity.CourierActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        courier.setOnClickListener {
            startActivity<CourierActivity>()
        }
    }
}
