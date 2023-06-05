package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.primera_view.databinding.ActivityMain3Binding

class MainActivity3: AppCompatActivity()  {

    private lateinit var binding: ActivityMain3Binding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)

        setContentView(binding.root)
    }

}