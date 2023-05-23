package com.example.primera_view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var button1 = findViewById<Button>(R.id.button1)

        var Buscar = findViewById<TextView>(R.id.buscar)
        button1.text = "INGRESAR"
        button1.setOnClickListener(){
            Buscar.text = "El evento sera ejecutado"
            Toast.makeText(this, "Este es un ejemplo" , Toast.LENGTH_SHORT).show()
        }
        var f = Snackbar.make(button1, "Este es otro mensaje" , Snackbar.LENGTH_LONG).setActionTextColor(145)
        f.show()

    }
}