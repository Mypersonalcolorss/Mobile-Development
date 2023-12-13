package com.example.mypersonalcolor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvAlreadyHaveAccount = findViewById<TextView>(R.id.tvAlreadyHaveAccount)
        tvAlreadyHaveAccount.setOnClickListener {
            // Intent to go back to LoginActivity
            val intent = Intent(this, AuthFragment::class.java)
            startActivity(intent)
            finish()
        }

        val btnRegisterSubmit = findViewById<Button>(R.id.btnRegisterSubmit)
        btnRegisterSubmit.setOnClickListener {
            // Handle the registration logic here
        }

// If you're providing Google Sign Up
        val btnRegisterGoogle = findViewById<Button>(R.id.btnRegisterGoogle)
        btnRegisterGoogle.setOnClickListener {
            // Handle the Google sign-up logic here
        }

    }


}
