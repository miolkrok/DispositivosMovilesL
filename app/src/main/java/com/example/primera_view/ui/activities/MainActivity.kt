package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.primera_view.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initClass(){

        binding.button1.setOnClickListener() {
            binding.buscar.text = "El codigo ejecuta correctamente"

            var f = Snackbar.make(
                binding.button1,
                "Este es otro mensaje" ,
                Snackbar.LENGTH_LONG)
            f.show()
        }
      //  var Buscar = findViewById<TextView>(R.id.buscar)


        //    Buscar.text = "El evento sera ejecutado"
        //    Toast.makeText(this, "Este es un ejemplo" , Toast.LENGTH_SHORT).show()
       // }

    }
}