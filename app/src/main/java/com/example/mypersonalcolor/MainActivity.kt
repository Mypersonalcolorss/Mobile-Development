package com.example.mypersonalcolor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            // Handle start button click
            Toast.makeText(this, "Starting...", Toast.LENGTH_SHORT).show()

            // Intent to start LandingActivity
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
        }

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            // Handle login button click
            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show()

            // You can also initiate a login process or navigate to a login screen if you have one
            // For example:
            // val loginIntent = Intent(this, LoginActivity::class.java)
            // startActivity(loginIntent)
        }
    }
}
