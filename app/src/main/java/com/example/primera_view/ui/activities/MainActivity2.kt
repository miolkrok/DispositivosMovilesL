package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        intent.extras.let {
            var name  = it?.getString("var1")
//            var name = it.getString("var1")
            Log.d("UCE", "${name} - INTRODUZCA NOMBRE")
//            var number = it?.getInt("var2")
            binding.tituloJefe.text = "Bienvenido: " + name.toString()
        }
        Log.d("UCE", "Entrando a Start")
        initClass()
    }

    private fun initClass(){
//        binding.tituloJefe.text = "El mejor"
//
//        var f = Snackbar.make(binding.botonJefe,
//        "Nunca lo dudes",
//        Snackbar.LENGTH_LONG)
//        f.show()

        binding.botonJefe.setOnClickListener{
            var intent = Intent(
                this,
                MainActivity::class.java
            )

            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}