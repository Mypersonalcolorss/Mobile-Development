package com.example.mypersonalcolor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            // Start your next activity
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }
}
