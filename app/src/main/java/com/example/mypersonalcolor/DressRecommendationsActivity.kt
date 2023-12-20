package com.example.mypersonalcolor

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonalcolor.adapter.DressRecommendationsAdapter
import com.example.mypersonalcolor.model.DressRecommendation
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.IOException
import org.json.JSONException
import org.json.JSONObject

class DressRecommendationsActivity : AppCompatActivity() {
    private lateinit var adapter: DressRecommendationsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dress_recommendations)

        val rvDressRecommendations = findViewById<RecyclerView>(R.id.rvDressRecommendations)
        rvDressRecommendations.layoutManager = LinearLayoutManager(this)
        adapter = DressRecommendationsAdapter(emptyList())
        rvDressRecommendations.adapter = adapter

        fetchDressRecommendations()
    }

    private fun fetchDressRecommendations() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val url = "https://server-utqhuaf4va-et.a.run.app/getUserStyle/$userId"

        val request = Request.Builder()
            .url(url)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("DressRecActivity", "Network request failed: ${e.message}")
                runOnUiThread {
                    Toast.makeText(this@DressRecommendationsActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: ""
                Log.d("DressRecActivity", "Network response: $responseBody")
                if (!response.isSuccessful) {
                    Log.e("DressRecActivity", "Network error: $responseBody")
                    runOnUiThread {
                        Toast.makeText(this@DressRecommendationsActivity, "Error: $responseBody", Toast.LENGTH_LONG).show()
                    }
                } else {
                    try {
                        val recommendations = parseResponse(responseBody)
                        Log.d("DressRecActivity", "Parsed recommendations: $recommendations")
                        runOnUiThread {
                            adapter.updateData(recommendations)
                        }
                    } catch (e: Exception) {
                        Log.e("DressRecActivity", "Error parsing response", e)
                        runOnUiThread {
                            Toast.makeText(this@DressRecommendationsActivity, "Error parsing response", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    private fun parseResponse(jsonString: String): List<DressRecommendation> {
        try {
            val jsonObject = JSONObject(jsonString)
            val jsonArray = jsonObject.getJSONArray("images")
            val imageUrls = mutableListOf<String>()

            for (i in 0 until jsonArray.length()) {
                val imageUrl = jsonArray.getString(i)
                imageUrls.add(imageUrl)
            }

            return imageUrls.map { imageUrl -> DressRecommendation(imageUrl) }
        } catch (e: JSONException) {
            Log.e("DressRecommendations", "Error parsing JSON", e)
            return emptyList() // Return an empty list on error
        }
    }

}

