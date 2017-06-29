package com.kotlin.mykotlin

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by Gandi on 2017/6/28.
 */
class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}