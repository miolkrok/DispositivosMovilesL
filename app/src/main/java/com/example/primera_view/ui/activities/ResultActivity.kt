package com.example.primera_view.ui.activities

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.btnOk.setOnClickListener {
            val i = Intent()
            i.putExtra("result", "Resultado Exitoso")
            setResult(RESULT_OK, i)
            finish()
        }
        binding.btnFalse.setOnClickListener {
            val i = Intent()
            i.putExtra("result", "Resultado Fallido")
            setResult(RESULT_CANCELED,i)
            finish()
        }
    }


}