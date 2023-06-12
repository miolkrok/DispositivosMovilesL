package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.primera_view.ui.fragments.FirstFragment
import com.example.primera_view.R
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
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inicio -> {
                    // Respond to navigation item 1 click
                    val frag = FirstFragment()
                    val transsaction = supportFragmentManager.beginTransaction()
                    transsaction.add(binding.frameContainer.id, frag)
                    transsaction.addToBackStack(null)
                    transsaction.commit()
                    true
                }
                R.id.fav -> {
                    var suma: Int =0
                    for(i in listOf(8,12,13)){
                        suma += i
                    }
                    // Respond to navigation item 2 click
                    Snackbar.make(binding.editTextText, "la suma es: ${suma}",Snackbar.LENGTH_LONG)
                        .show()
                    true
                }
                R.id.api -> {
                    // Respond to navigation item 2 click
                    var suma: Int =0
                    for(i in listOf(30,12,13)){
                        suma += i
                    }
                    Snackbar.make(binding.editTextText, "la suma es: ${suma}",Snackbar.LENGTH_LONG)
                        .show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}