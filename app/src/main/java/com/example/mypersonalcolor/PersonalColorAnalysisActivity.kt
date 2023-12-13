package com.example.mypersonalcolor

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class PersonalColorAnalysisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_color_analysis)

        val gridView: GridView = findViewById(R.id.gridViewPersonalColor)
        gridView.adapter = PersonalColorAdapter(this)

        // Set an item click listener if needed
        gridView.setOnItemClickListener { _, _, position, _ ->
            // Handle click event
        }
    }
}
