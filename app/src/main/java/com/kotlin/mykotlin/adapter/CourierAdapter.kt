package com.kotlin.mykotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.kotlin.mykotlin.data.CourierData
import com.kotlin.mykotlin.data.CourierProcess
import android.view.LayoutInflater
import android.widget.TextView
import com.kotlin.mykotlin.R
import kotlinx.android.synthetic.main.item_courier.view.*
import org.jetbrains.anko.find


/**
 * Created by Gandi on 2017/6/19.
 */
class CourierAdapter(val mContext: Context, val items: List<CourierProcess>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(mContext)
        val view = inflater.inflate(R.layout.item_courier, null)
        val tv_courier_time: TextView = view.find(R.id.tv_courier_time)
        val tv_courier_process: TextView = view.find(R.id.tv_courier_process)
        with(getItem(p0)) {
            tv_courier_time.text = time
            tv_courier_process.text = context
        }

        return view
    }

    override fun getItem(p0: Int): CourierProcess = items[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = items.size


}