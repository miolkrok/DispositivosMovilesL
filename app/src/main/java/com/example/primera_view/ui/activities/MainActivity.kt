package com.example.primera_view.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast

import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityMainBinding
import com.example.primera_view.login.validator.LoginValidator
import com.example.primera_view.ui.utilities.MyLocationManager
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.CurrentLocationRequest

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.gms.tasks.Task
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
        by preferencesDataStore(
            name = "settings"
        )

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest : LocationRequest
    private lateinit var locationCallback : LocationCallback
    private lateinit var client:SettingsClient
    private lateinit var locationSettingsRequest : LocationSettingsRequest

    private lateinit var auth: FirebaseAuth
    private val TAG="UCE"

    private var currentLocation: Location? =null

    private var speechToText = registerForActivityResult(StartActivityForResult()){activityResult ->
        val sn = Snackbar.make(
            binding.textView," ",
            Snackbar.LENGTH_LONG
        )
        var message = ""
        when(activityResult.resultCode){
            RESULT_OK->{
                val msg = activityResult.data?.getStringArrayExtra(
                    RecognizerIntent.EXTRA_RESULTS
                )?.get(0)
                    .toString()
                if(msg.isNotEmpty()){
                    val intent = Intent(Intent.ACTION_WEB_SEARCH)
                    intent.setClassName("com.google.android.googlequicksearchbox",
                        "com.google.android.googlequicksearchbox.SearchActivity")
                    intent.putExtra(SearchManager.QUERY, msg)
                    startActivity(intent)
                }
            }
            RESULT_CANCELED->{
                message = "Proceso cancelado"
                sn.setBackgroundTint(resources.getColor(R.color.buttonCancel))
            }
            else->{
                message = "Ocurrio un error"
                sn.setBackgroundTint(resources.getColor(R.color.buttonCancel))
            }
        }
        sn.setText(message)
        sn.show()

    }

    @SuppressLint("MissingPermission")
    private var locationContract = registerForActivityResult(RequestPermission()){isGranted ->
        when(isGranted){
            true ->{
//                val alert = AlertDialog.Builder(this).apply {
//                    setTitle("Notificacion")
//                    setMessage("Por favor verifique que el gps este activo")
//                    setPositiveButton("verificar") { dialog, id->
//                        val i = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                        startActivity(i)
//                        dialog.dismiss()
//                    }
//                    setCancelable(false)
//                }.show()
                client.checkLocationSettings(locationSettingsRequest).apply {
                    addOnSuccessListener {
                    val task = fusedLocationProviderClient.lastLocation
                    task.addOnSuccessListener {location ->
                        fusedLocationProviderClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            Looper.getMainLooper())
                    }
                }
                    addOnFailureListener {ex->
                        if(ex is ResolvableApiException){
//                        startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                            ex.startResolutionForResult(
                            this@MainActivity,
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                        )
//                        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }


//                        com.google.android.material.R.style.ThemeOverlay_MaterialAlertDialog_Material3_Title_Icon
//                        val alert = AlertDialog.Builder(this, com.google.android.material.R.style.ThemeOverlay_Material3_Dialog)
//                        alert.apply {
//                            setTitle("Alerta")
//                            setMessage("Existe un problema de posicionamiento global en el sistema")
//                            setPositiveButton("OK") {dialog,id ->
//                                dialog.dismiss()
//                            }
//                            setNegativeButton("Cancelar"){dialog,id ->
//                                dialog.dismiss()
//                            }
//                            setCancelable(false)
////                        setNegativeButton("")
//                        }.create()
//                        alert.show()

//                        location.longitude
//                        location.latitude
//                    val a = Geocoder(this)
//                    a.getFromLocation(it.latitude,it.longitude, 1)
                }
//                client.checkLocationSettings(locationSettingsRequest).addOnFailureListener {
//                        ex->
//                    if(ex is ResolvableApiException){
//                        ex.startResolutionForResult(
//                            this@MainActivity,
//                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED
//                        )
//                    }
                }
            }
            shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)->{
                Snackbar.make(binding.editName,
                    "Ayude con el permiso por favor",
                    Snackbar.LENGTH_LONG)
                    .show()

            }
            false ->{
                Snackbar.make(binding.editName,
                    "denegado",
                    Snackbar.LENGTH_LONG)
                    .show()
            }

        }

    }
    var a =2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.button1.setOnClickListener {
//            authWithFirebaseEmail(binding.editName.text.toString(),binding.editPass.text.toString())
            signInWithEmailAndPassword(binding.editName.text.toString(),binding.editPass.text.toString())
        }

        Log.d("UCE", "Entrando a Create")
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            1000)
//            .setMaxUpdates(3)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                if(locationResult !=null){
                    locationResult.locations.forEach {location->
                        currentLocation = location
                        Log.d("UCE","Ubicacion : ${location.latitude}," +
                        "${location.longitude}")
                    }
                }
            }
        }
        client = LocationServices.getSettingsClient(this)
        locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest).build()
    }

    private fun authWithFirebaseEmail(email:String,password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
//                    updateUI(null)
                }
            }
    }
    private fun signInWithEmailAndPassword(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
//                    updateUI(user)
                    startActivity(Intent(this,BiometricActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
//                    updateUI(null)
                }
            }
    }

    private fun recoveryPasswordWithEmail(email:String){
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    Toast.makeText(this,"Correo de recuperacion enviado correctamente",
                            Toast.LENGTH_SHORT).show()
                    MaterialAlertDialogBuilder(this).apply {
                        setTitle("Alert")
                        setMessage("Correo de recuperacion enviado correctamente")
                        setCancelable(true)
                    }.show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
//        initClass()

//        val db = Primeraview.getDbInstance()
//        db.marvelDao()

    }



    @SuppressLint("MissingPermission")
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
//        val locationContract = registerForActivityResult(RequestPermission()){isGranted ->
////            if(isGranted == true){
////                val task = fusedLocationProviderClient.lastLocation
////                task.addOnSuccessListener {
////                    if(task.result != null){
////                        Snackbar.make(
////                            binding.editName,
////                            "${it.latitude}, ${it.longitude}",
////                            Snackbar.LENGTH_LONG)
////                            .show()
////                    }else {
////                        Snackbar.make(binding.editName,
////                            "Encienda el GPS por favor",
////                            Snackbar.LENGTH_LONG)
////                            .show()
////                    }
////
////                }
////
////            }else {
////
////                Snackbar.make(binding.editName,
////                    "denegado",
////                    Snackbar.LENGTH_LONG)
////                    .show()
////            }
//            when(isGranted){
//                true ->{
//                    fusedLocationProviderClient.lastLocation.addOnSuccessListener {
//                        it.longitude
//                        it.latitude
//                        val a = Geocoder(this)
//                        a.getFromLocation(it.latitude,it.longitude, 1)
//                    }
//                }
//                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)->{
//                    Snackbar.make(binding.editName,
//                        "Ayude con el permiso por favor",
//                        Snackbar.LENGTH_LONG)
//                        .show()
//
//                }
//                false ->{
//                    Snackbar.make(binding.editName,
//                        "denegado",
//                        Snackbar.LENGTH_LONG)
//                        .show()
//                }
//
//            }
//
//        }
        binding.button3.setOnClickListener {
            locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:0123456789"))
//            //Uri.parse("geo: -0.2006288,-78.5786066")
//            //Uri.parse("https://www.google.com.ec/")
//
//            startActivity(intent)
//        }
//            val intent = Intent(Intent.ACTION_WEB_SEARCH)
//            intent.setClassName("com.google.android.googlequicksearchbox",
//            "com.google.android.googlequicksearchbox.SearchActivity")
//            intent.putExtra(SearchManager.QUERY, "UCE")
//            startActivity(intent)


        }
        val appResultLocal = registerForActivityResult(StartActivityForResult()){resultActivity ->

            val sn = Snackbar.make(
                binding.textView," ",
                Snackbar.LENGTH_LONG
            )
            val message =when(resultActivity.resultCode) {
                RESULT_OK ->{
                    sn.setBackgroundTint(resources.getColor(R.color.facebook))
                    resultActivity.data?.getStringExtra("result")
                        .orEmpty()


                }
                RESULT_CANCELED -> {
                    sn.setBackgroundTint(resources.getColor(R.color.buttonCancel))
                    resultActivity.data?.getStringExtra("result")
                        .orEmpty()
                }
                else->{
                    "Resultado Dudoso"
                }
            }
            sn.setText(message)
            sn.show()


        }

//        val speechToText = registerForActivityResult(StartActivityForResult()){activityResult ->
//            val sn = Snackbar.make(
//                binding.textView," ",
//                Snackbar.LENGTH_LONG
//            )
//            var message = ""
//            when(activityResult.resultCode){
//                RESULT_OK->{
//                    val msg = activityResult.data?.getStringArrayExtra(
//                        RecognizerIntent.EXTRA_RESULTS
//                    )?.get(0)
//                        .toString()
//                    if(msg.isNotEmpty()){
//                        val intent = Intent(Intent.ACTION_WEB_SEARCH)
//                        intent.setClassName("com.google.android.googlequicksearchbox",
//                            "com.google.android.googlequicksearchbox.SearchActivity")
//                        intent.putExtra(SearchManager.QUERY, msg)
//                        startActivity(intent)
//                    }
//                }
//                RESULT_CANCELED->{
//                    message = "Proceso cancelado"
//                    sn.setBackgroundTint(resources.getColor(R.color.buttonCancel))
//                }
//                else->{
//                    message = "Ocurrio un error"
//                    sn.setBackgroundTint(resources.getColor(R.color.buttonCancel))
//                }
//            }
//            sn.setText(message)
//            sn.show()
//
//        }

        binding.button4.setOnClickListener {

            val intentSpeech = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intentSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intentSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )
            intentSpeech.putExtra(
                RecognizerIntent.EXTRA_PROMPT,"Di algo..."
            )
            speechToText.launch(intentSpeech)
//            val resIntent = Intent(this, ResultActivity::class.java)
//            appResultLocal.launch(resIntent)

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

    override fun onPause() {
        super.onPause()
        fusedLocationProviderClient
            .removeLocationUpdates(locationCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    private fun test(){
        var location = MyLocationManager(this)
        location.getUserLocation()
    }
}