package com.kickhead.cameragallery

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.kickhead.cameragallery.extensions.extraContainsKey
import com.kickhead.cameragallery.extensions.getExtraString
import com.kickhead.cameragallery.helper.SharedPreferencesHelper
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class SecondActivity : AppCompatActivity() {
    private var timer : Timer?=null
    companion object {
        fun startActivity(context: Context?,uri: Uri?){
            context?.startActivity(Intent(context, SecondActivity::class.java).apply {
                putExtra(AppConstants.KEY_URI, uri.toString())
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        getBundleData()
        setCountTextAfter5Sec()
    }

    private fun getBundleData(){
        if(extraContainsKey(AppConstants.KEY_URI)){
            val imageUri = getExtraString(AppConstants.KEY_URI)
            setImageUri(imageUri?.toUri())
        }
    }

    private fun setImageUri(uri: Uri?){
        ivPhoto?.setImageURI(uri)
    }

    private fun setCountTextAfter5Sec(){
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
             Handler(Looper.getMainLooper()).post {
                 var count = SharedPreferencesHelper.getIntWithDefValue(SharedPrefConstant.COUNT,0)
                 count += 1
                 tvCounter?.text = "$count"
                 SharedPreferencesHelper.putInt(SharedPrefConstant.COUNT,count)
             }
            }
        }, 0, 5000)

    }


    override fun onDestroy() {
        timer?.cancel()
        timer == null
        super.onDestroy()
    }
}