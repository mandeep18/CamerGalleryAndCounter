package com.kickhead.cameragallery

import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.res    ult.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val GALLERY_REQUEST_CODE = 100
    private val CAMERA_REQUEST_CODE = 200
    private val RequestPermissionCode = 300
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableRuntimePermission()

        setListeners()
    }

    private fun setListeners(){
        btnCamera?.setOnClickListener(onClickListener)
        btnGallery?.setOnClickListener(onClickListener)
    }

    private val onClickListener:View.OnClickListener = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            when(p0?.id){
                R.id.btnCamera -> dispatchTakePictureIntent()
                R.id.btnGallery -> openGalleryIntent()
            }
        }
    }

    private fun openGalleryIntent() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Select File"), GALLERY_REQUEST_CODE)
    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(resultCode== RESULT_OK){
            if(requestCode == GALLERY_REQUEST_CODE || requestCode == CAMERA_REQUEST_CODE){
                SecondActivity.startActivity(this,intent?.data)
                Log.d("TAG","${intent?.data}")
            }
        }
    }

    private fun enableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this@MainActivity, Manifest.permission.CAMERA)) {
            Toast.makeText(this@MainActivity, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show()
        } else {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.CAMERA), RequestPermissionCode)
        }
    }

}