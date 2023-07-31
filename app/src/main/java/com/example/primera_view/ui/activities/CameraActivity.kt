package com.example.primera_view.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityCameraBinding

class CameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCapture.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraResult.launch(intent)
        }

        binding.imageView2.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "HOLA COMO ESTAS?")
            shareIntent.setType("text/plain")
            startActivity(Intent.createChooser(shareIntent,"Compartir"))
        }
    }

    private val cameraResult =registerForActivityResult(StartActivityForResult()){result ->

        when(result.resultCode){
            Activity.RESULT_OK->{
                val image = result.data?.extras?.get("data") as Bitmap
                binding.imageView2.setImageBitmap(image)
            }
            Activity.RESULT_CANCELED->{

            }
        }
    }
}