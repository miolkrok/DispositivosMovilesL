package com.example.primera_view.animiolknova.ui.activities

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityAniMiolkNovaLoginBinding
import com.example.primera_view.ui.activities.BiometricActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AniMiolkNovaLoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAniMiolkNovaLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAniMiolkNovaLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.loginButton.setOnClickListener {
            signInWithEmailAndPassword(binding.emailEditText.text.toString(),binding.passwordEditText.text.toString())
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
                    startActivity(Intent(this, AniMiolkNovaMainActivity::class.java))
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
}