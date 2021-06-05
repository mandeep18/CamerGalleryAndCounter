package com.kickhead.cameragallery.helper

import com.kickhead.cameragallery.AppController

object SharedPreferencesHelper {

    fun getString(key:String):String?{
        return SharedPreferencesManager.with(context = AppController.appController)
            .getString(key, null)
    }

    fun getStringWithDefValue(key:String, defValue:String):String?{
        return SharedPreferencesManager.with(context = AppController.appController)
            .getString(key, defValue)
    }

    fun getInt(key:String):Int{
        return SharedPreferencesManager.with(context = AppController.appController).getInt(key, 1)
    }
    fun getIntWithDefValue(key:String, dev:Int):Int{
        return SharedPreferencesManager.with(context = AppController.appController).getInt(key, dev)
    }

    fun putInt(key: String, value:Int){
        SharedPreferencesManager.with(context = AppController.appController).edit().putInt(key, value).apply()
    }
}