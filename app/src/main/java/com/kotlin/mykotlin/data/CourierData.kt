package com.kotlin.mykotlin.data


/**
 * Created by Gandi on 2017/6/16.
 */
data class CourierData(val message: String, val nu: String, val ischeck: Int, val condition: String, val com: String, val status: Int, val state: Int,val data:List<CourierProcess>)

data class CourierProcess(val time:String,val ftime:String,val context: String,val location:String)