package com.example.primera_view.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.primera_view.databinding.ActivityMainBinding
import com.example.primera_view.login.validator.LoginValidator
import com.example.primera_view.ui.utilities.Primeraview
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
        by preferencesDataStore(
            name = "settings"
        )

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
//        val db = Primeraview.getDbInstance()
//        db.marvelDao()

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

//        binding.button1.setOnClickListener{
//            var intent = Intent(
//                this,
//                MainActivity2::class.java
//            )
//            intent.putExtra("var1" , binding.edit.text.toString())
//
//            startActivity(intent)
//        }
        binding.button1.setOnClickListener{
            val loginVal = LoginValidator()
            val check = loginVal.checkLogin( binding.editName.text.toString(), binding.editPass.text.toString())
//            if ( binding.editName.text.toString() == "admin" && binding.editName.text.toString() == "admin"){
            if (check){
                lifecycleScope.launch(Dispatchers.IO) {
                    saveDataStore(binding.editName.text.toString())
                }


                var intent = Intent(
                    this,
                    MainActivity2::class.java
                )
                intent.putExtra("var1" ,
                    ""
//                binding.edit_.text.toString()
                )
                intent.putExtra("var2", 2)
                startActivity(intent)
            }else{
                Snackbar.make(binding.textView, "Usuario o contraseña invalidos", Snackbar.LENGTH_LONG).show()
            }
        }

    }

    private suspend fun saveDataStore(stringData: String){
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("usuario")] = stringData
            prefs[stringPreferencesKey("session")] = UUID.randomUUID().toString()
            prefs[stringPreferencesKey("email")] = "DispositivosMoviles@uce.edu.ec"
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}