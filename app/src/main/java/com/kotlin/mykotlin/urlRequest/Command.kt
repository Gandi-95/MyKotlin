package com.kotlin.mykotlin.urlRequest

import com.kotlin.mykotlin.data.CourierData
import com.kotlin.mykotlin.model.City

/**
 * Created by Gandi on 2017/6/16.
 */
interface Command<T> {
    fun execute(): T?
}