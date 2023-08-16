package com.example.primera_view.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityBiometricBinding
import com.example.primera_view.ui.viewmodels.BiometricViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class BiometricActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityBiometricBinding

    private val biometricViewModel by viewModels<BiometricViewModel>()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_biometric)

        binding.btnAutentication.setOnClickListener {
//            lifecycleScope.launch {
//                biometricViewModel.chargingData()
//            }
            autenticateBiometric()
        }
        biometricViewModel.isLoading.observe(this){isLoading ->
            if (isLoading){
                binding.lytMain.visibility = View.GONE
                binding.lytMainCopia.visibility = View.VISIBLE
            }
            else{
                binding.lytMain.visibility = View.VISIBLE
                binding.lytMainCopia.visibility = View.GONE
            }


        }
        lifecycleScope.launch {
            biometricViewModel.chargingData()
        }

    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun autenticateBiometric() {

        if (checkBiometric()){


        val executor = ContextCompat.getMainExecutor(this)

        val biometricPrompt = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticacion requerida")
            .setSubtitle("Ingrese su huella digital")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
            .setNegativeButtonText("Cancelar")
            .build()

        val biometricManager = BiometricPrompt(this,
            executor,
            object: BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    startActivity(
                        Intent(this@BiometricActivity,
                        CameraActivity::class.java)
                    )
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                }
            })
        biometricManager.authenticate(biometricPrompt)

        }else {
            Snackbar.make(binding.btnAutentication,"No existen los requisitos necesarios",
            Snackbar.LENGTH_LONG)
                .show()
        }
    }


    private fun checkBiometric():Boolean{
        var returnValid: Boolean = false
        val biometricManager = BiometricManager.from(this)
        when(biometricManager.canAuthenticate(
            BIOMETRIC_STRONG or DEVICE_CREDENTIAL
        )){
            BiometricManager.BIOMETRIC_SUCCESS->{
                returnValid = true
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE->{
                returnValid = false
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE->{
                returnValid = false
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED ->{

                val intentPromp = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                intentPromp.putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
                startActivity(intentPromp)
                returnValid = false

            }
        }
        return false
    }
}