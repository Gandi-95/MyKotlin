package com.kotlin.mykotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.kotlin.mykotlin.R
import com.kotlin.mykotlin.adapter.CourierAdapter
import com.kotlin.mykotlin.data.CourierData
import com.kotlin.mykotlin.data.CourierProcess
import com.kotlin.mykotlin.urlRequest.CourierUrlRequest
import kotlinx.android.synthetic.main.activity_courier.*
import org.jetbrains.anko.doAsyncResult
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class CourierActivity : AppCompatActivity() {

    companion object {
        private val courierCompanyName = listOf("申通", "EMS", "顺丰", "圆通", "中通", "韵达", "天天", "汇通", "全峰", "德邦", "宅急送")
        private val courierCompany = listOf("shentong", "ems", "shunfeng", "yuantong", "zhongtong", "yunda", "tiantian", "huitongkuaidi", "quanfengkuaidi", "debangwuliu", "zhaijisong")
    }

    var courierData: MutableList<CourierProcess> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courier)

        sp_courier_company.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courierCompanyName)
        val listAdapter = CourierAdapter(this, courierData)
        lv_courier_process.adapter = listAdapter

        lv_courier_process.setOnItemClickListener {
            _, _, position, _ ->
            toast(listAdapter.getItem(position).context)
        }

        btn_courier_query.setOnClickListener {
            if (et_courier_num.text!=null) {
                courierData.clear()
                doAsyncResult {
                    val courierRequest = CourierUrlRequest(courierCompany[sp_courier_company.selectedItemPosition], et_courier_num.text.toString()).execute()
                    uiThread {
                        with(courierRequest) {
                            tv_courier_state.text = nu + "\n" + message
                            courierData.addAll(data)
                            listAdapter.notifyDataSetChanged()
                        }
                    }

                }
            }
        }

    }
}




