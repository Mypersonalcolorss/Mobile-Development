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

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val featureButton = findViewById<Button>(R.id.featureButton)
        featureButton.setOnClickListener {
            // Intent to start PersonalColorAnalysisActivity
            val intent = Intent(this, PersonalColorAnalysisActivity::class.java)
            startActivity(intent)
        }


    }
}
