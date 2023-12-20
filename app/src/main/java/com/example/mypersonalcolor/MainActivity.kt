package com.example.mypersonalcolor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    // Declare an instance of FirebaseAuth
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the FirebaseAuth instance
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is already signed in, redirect to the feature activity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        }

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            // Handle start button click
            Toast.makeText(this, "Starting...", Toast.LENGTH_SHORT).show()

            // Intent to start LandingActivity
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
        }

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // User is signed in
            // Redirect to the LandingActivity or another appropriate activity
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
            finish() // Finish MainActivity so user can't go back by pressing the back button
        }
    }


}

