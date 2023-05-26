package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primera_view.databinding.ActivityMain2Binding
import com.google.android.material.snackbar.Snackbar

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    private fun initClass(){
        binding.tituloJefe.text = "El mejor"

        var f = Snackbar.make(binding.botonJefe,
        "Nunca lo dudes",
        Snackbar.LENGTH_LONG)
        f.show()
    }
}