package com.android.firebaseimplement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.firebaseimplement.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnLogin.setOnClickListener{
            val email = binding.etEmLogin.text.toString()
            val password = binding.etPwLogin.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task -> if (task.isSuccessful){
                    Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show()
                    //ganti setelah rebuild main activity
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                }else {
                    Log.w("Login", "createUserWithEmail:failure", task.getException())
                    Toast.makeText(this, "Authentication Failed.",Toast.LENGTH_SHORT).show()
                }
                }
        }
        binding.tvAskNtveAccount.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}