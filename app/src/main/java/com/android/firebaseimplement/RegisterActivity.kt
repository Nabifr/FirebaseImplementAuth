package com.android.firebaseimplement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.firebaseimplement.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnRegister.setOnClickListener{
            val email = binding.etEmRegister.text.toString()
            val password = binding.etPwRegister.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task -> if  (task.isSuccessful){
                Toast.makeText(this, "Berhasil Membuat Akun",Toast.LENGTH_SHORT).show()
                    //ganti setelah rebuild login activity
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("Register", "createUserWithEmail:failure", task.getException())
                    Toast.makeText(this, "Authentication Failed.",Toast.LENGTH_SHORT).show()

                }

        }

    }
        binding.tvAskHveAccount.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
}
}
}