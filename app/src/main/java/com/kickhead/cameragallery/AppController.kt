package com.kickhead.cameragallery

import android.app.Application
import android.content.res.Configuration


class AppController:Application() {

    companion object{
        val TAG:String = AppController::class.java.simpleName
        var appController:AppController? = null
    }

    override fun onCreate() {
        super.onCreate()

        appController = this
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}