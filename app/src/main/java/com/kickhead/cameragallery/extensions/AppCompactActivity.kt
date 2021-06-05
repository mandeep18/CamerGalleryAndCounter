package com.kickhead.cameragallery.extensions

import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

fun AppCompatActivity.extraContainsKey(key:String):Boolean {
    return when(intent?.extras?.containsKey(key)) {
        true -> true
        else -> false
    }
}
fun AppCompatActivity.getExtraSerializable(key:String): Serializable? {
    return intent?.extras?.getSerializable(key)
}
fun AppCompatActivity.getExtraString(key:String): String? {
    return intent?.extras?.getString(key)
}