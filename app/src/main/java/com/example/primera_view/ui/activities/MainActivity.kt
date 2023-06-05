package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.primera_view.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var a =2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("UCE", "Entrando a Create")
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }



    private fun initClass(){

//        binding.button1.setOnClickListener() {
//            binding.buscar.text = "El codigo ejecuta correctamente"
//
//            var f = Snackbar.make(
//                binding.button1,
//                "Este es otro mensaje" ,
//                Snackbar.LENGTH_LONG)
//            f.show()
//        }
      //  var Buscar = findViewById<TextView>(R.id.buscar)


        //    Buscar.text = "El evento sera ejecutado"
        //    Toast.makeText(this, "Este es un ejemplo" , Toast.LENGTH_SHORT).show()
       // }

        binding.button1.setOnClickListener{
            var intent = Intent(
                this,
                MainActivity2::class.java
            )
            intent.putExtra("var1" , binding.edit.text.toString())

            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}