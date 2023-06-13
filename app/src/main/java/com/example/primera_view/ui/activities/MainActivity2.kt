package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.primera_view.ui.fragments.FirstFragment
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityMain2Binding
import com.example.primera_view.ui.fragments.FragmentSecond
import com.example.primera_view.ui.fragments.FragmentThird
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
                    val frag2 = FragmentSecond()
                    val transsaction2 = supportFragmentManager.beginTransaction()
                    transsaction2.add(binding.frameContainer.id, frag2)
                    transsaction2.addToBackStack(null)
                    transsaction2.commit()
                    true
                }
                R.id.api -> {
                    // Respond to navigation item 2 click
                    val frag3 = FragmentThird()
                    val transsaction3 = supportFragmentManager.beginTransaction()
                    transsaction3.add(binding.frameContainer.id, frag3)
                    transsaction3.addToBackStack(null)
                    transsaction3.commit()
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