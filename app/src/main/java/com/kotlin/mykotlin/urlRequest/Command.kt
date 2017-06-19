package com.kotlin.mykotlin.urlRequest

/**
 * Created by Gandi on 2017/6/16.
 */
interface Command<T>{
    fun execute():T
}