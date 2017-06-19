package com.kotlin.mykotlin.urlRequest

import android.util.Log
import com.google.gson.Gson
import com.kotlin.mykotlin.data.CourierData
import java.net.URL

/**
 * Created by Gandi on 2017/6/16.
 */
class CourierUrlRequest(val CourierCompany: String, val CourierNumber: String) : Command<CourierData> {

    val TAG = "CourierUrlRequest"

    override fun execute(): CourierData {
        val CourierUrl = "http://www.kuaidi100.com/query?type=$CourierCompany&postid=$CourierNumber"
        Log.i(TAG,"CourierUrl:"+CourierUrl)
        val CourierJson = URL(CourierUrl).readText()
        Log.i(TAG,"CourierJson:"+CourierJson)
        return Gson().fromJson(CourierJson, CourierData::class.java)
    }

}


//http://www.kuaidi100.com/query?type=shentong&postid=3331548756000    测试用例
//ps:快递公司编码:申通="shentong" EMS="ems" 顺丰="shunfeng" 圆通="yuantong" 中通="zhongtong" 韵达="yunda" 天天="tiantian" 汇通="huitongkuaidi" 全峰="quanfengkuaidi" 德邦="debangwuliu" 宅急送="zhaijisong"