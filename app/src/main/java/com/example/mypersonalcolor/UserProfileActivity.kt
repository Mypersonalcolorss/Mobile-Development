package com.example.mypersonalcolor

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.Calendar

class UserProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        auth = FirebaseAuth.getInstance()

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the Up button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed() // Handle the back button event
        }

        val btnEditProfile: ImageButton = findViewById(R.id.btnEditProfile)
        btnEditProfile.setOnClickListener {
            // Handle the edit profile event
        }

        val etFirstName: EditText = findViewById(R.id.etFirstName)
        val etLastName: EditText = findViewById(R.id.etLastName)
        val etPhoneNumber: EditText = findViewById(R.id.etPhoneNumber)
        val spinnerGender: Spinner = findViewById(R.id.spinnerGender)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        val btnLogout: Button = findViewById(R.id.btnLogout)

        // Initialize the spinner with gender options
        ArrayAdapter.createFromResource(
            this,
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGender.adapter = adapter
        }

        // Set up the DatePicker button
        btnDatePicker.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, monthOfYear, dayOfMonth ->
                // Format and set the date in the button text
                btnDatePicker.text = String.format("%d-%d-%d", selectedYear, monthOfYear + 1, dayOfMonth)
            }, year, month, day).show()
        }

        // Set up the Logout button
        btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Populate the user profile fields if the user is logged in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Assuming you have methods to get user data, populate the fields here
            etFirstName.setText(currentUser.displayName)
            // ... populate other fields
        } else {
            // No user is logged in
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
