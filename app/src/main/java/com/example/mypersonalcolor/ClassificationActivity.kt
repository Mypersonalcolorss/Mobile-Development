package com.example.mypersonalcolor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonalcolor.adapter.ColorPaletteAdapter
import com.example.mypersonalcolor.model.ColorPalette
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ClassificationActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var colorPaletteAdapter: ColorPaletteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification)

        recyclerView = findViewById(R.id.rvClassifications)
        recyclerView.layoutManager = LinearLayoutManager(this)
        colorPaletteAdapter = ColorPaletteAdapter(emptyList())
        recyclerView.adapter = colorPaletteAdapter

        fetchUserCategoryAndClassificationData()
    }

    private fun fetchUserCategoryAndClassificationData() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        FirebaseFirestore.getInstance().collection("User").document(userId).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val predictedCategory = document.getLong("predictedCategory")?.toString()
                    predictedCategory?.let { category ->
                        val colorPalette = getColorPaletteForCategory(category)
                        colorPaletteAdapter.updateData(colorPalette)
                    }
                }
            }
            .addOnFailureListener {
                // Handle failure
            }
    }

    private fun getColorPaletteForCategory(category: String): List<ColorPalette> {
        return when (category) {
            "1" -> listOf(ColorPalette("Baby Pink", "#FFC0CB"),
                ColorPalette("Lavender", "#E6E6FA"),
                ColorPalette("Light Gray", "#D3D3D3"),
                ColorPalette("Soft Blue", "#ADD8E6"),
                ColorPalette("Mint Green", "#00FF7F"),
                ColorPalette("Rose Gold", "#B76E79"),
                ColorPalette("Pastel Yellow", "#FFFD96"),
                ColorPalette("Peach", "#FFDAB9"),
                ColorPalette("Turquoise", "#40E0D0"),
                ColorPalette("Lilac", "#C8A2C8"))
            "2" -> listOf(ColorPalette("Light Pink", "#FFB6C1"),
                ColorPalette("Peach", "#FFDAB9"),
                ColorPalette("Soft Yellow", "#FFFFE0"),
                ColorPalette("Mint Green", "#98FB98"),
                ColorPalette("Sky Blue", "#87CEEB"),
                ColorPalette("Lavender", "#E6E6FA"),
                ColorPalette("Coral", "#FF6F61"),
                ColorPalette("Butter Yellow", "#FFFACD"),
                ColorPalette("Pale Green", "#98FB98"),
                ColorPalette("Aqua Blue", "#00FFFF"))
            "3" -> listOf(ColorPalette("Burnt Orange", "#D2691E"),
                ColorPalette("Rich Burgundy", "#800000"),
                ColorPalette("Olive Green", "#556B2F"),
                ColorPalette("Mustard Yellow", "#FFDB58"),
                ColorPalette("Terracotta", "#E2725B"),
                ColorPalette("Rust Red", "#B7410E"),
                ColorPalette("Deep Brown", "#5C3317"),
                ColorPalette("Forest Green", "#228B22"),
                ColorPalette("Pumpkin", "#FF7518"),
                ColorPalette("Maroon", "#800000"))
            "4" -> listOf(ColorPalette("Royal Blue", "#4169E1"),
                ColorPalette("Icy Silver", "#C0C0C0"),
                ColorPalette("Deep Purple", "#800080"),
                ColorPalette("Charcoal Gray", "#36454F"),
                ColorPalette("Emerald Green", "#008000"),
                ColorPalette("Navy Blue", "#000080"),
                ColorPalette("Burgundy", "#800020"),
                ColorPalette("Frosty White", "#F0F8FF"),
                ColorPalette("Midnight Black", "#000000"),
                ColorPalette("Steel Gray", "#708090"))
            else -> emptyList() // Handle unexpected values
        }
    }
}
