package com.example.mypersonalcolor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.firebase.auth.FirebaseAuth
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class FaceAnalysisActivity : AppCompatActivity() {

    private lateinit var ivUserFace: ImageView
    private lateinit var btnAutomatic: Button
    private lateinit var tvSkinTypeMessage: TextView
    private val cameraRequestCode = 100
    private var photoFile: File? = null
    private lateinit var btnDressRecommendations: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_face_analysis)

        ivUserFace = findViewById(R.id.ivUserFace)
        val btnAnalyticFaceColor: Button = findViewById(R.id.btnAnalyticFaceColor)
        btnAutomatic = findViewById(R.id.btnAutomatic)
        btnAutomatic.isEnabled = false // Disable the button initially
        tvSkinTypeMessage = findViewById(R.id.tvSkinTypeMessage)

        btnAnalyticFaceColor.setOnClickListener {
            openCameraForSelfie()
        }

        btnAutomatic.setOnClickListener {
            startAutomaticFeature()
        }
        btnDressRecommendations = findViewById(R.id.btnDressRecommendations)
        btnDressRecommendations.setOnClickListener {
            navigateToDressRecommendations()
        }
    }

    private fun openCameraForSelfie() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), cameraRequestCode)
        } else {
            dispatchTakePictureIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            photoFile = try {
                createImageFile()
            } catch (ex: IOException) {
                null
            }
            photoFile?.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "${applicationContext.packageName}.fileprovider",
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, cameraRequestCode)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File = getExternalFilesDir(Environment.DIRECTORY_PICTURES) ?: throw IOException("Unable to access storage directory.")
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).also {
            photoFile = it
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == cameraRequestCode && resultCode == RESULT_OK) {
            photoFile?.also {
                val photoURI = Uri.fromFile(it)
                ivUserFace.setImageURI(photoURI)
                btnAutomatic.isEnabled = true // Enable the button
            }
        }
    }

    private fun startAutomaticFeature() {
        // Obtain the user ID from Firebase Authentication
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return

        // Create the request
        val request = Request.Builder()
            .url("https://server-utqhuaf4va-et.a.run.app/getSkinResult/$userId")
            .build()

        // Send the request
        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(this@FaceAnalysisActivity, "Failed to connect to the server: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string() ?: ""
                if (!response.isSuccessful) {
                    runOnUiThread {
                        Toast.makeText(this@FaceAnalysisActivity, "Error: $responseBody", Toast.LENGTH_LONG).show()
                    }
                } else {
                    // Parse the response
                    try {
                        val jsonObject = JSONObject(responseBody)
                        val skinTypeMessage = jsonObject.getString("skinType")
                        runOnUiThread {
                            tvSkinTypeMessage.text = skinTypeMessage
                            tvSkinTypeMessage.visibility = View.VISIBLE
                            btnDressRecommendations.visibility = View.VISIBLE
                        }

                        runOnUiThread {
                            Toast.makeText(this@FaceAnalysisActivity, skinTypeMessage, Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        runOnUiThread {
                            Toast.makeText(this@FaceAnalysisActivity, "Error parsing response", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    private fun navigateToDressRecommendations() {
        val intent = Intent(this, DashboardResultActivity::class.java)
        startActivity(intent)
        intent.putExtra("ANALYSIS_COMPLETED", true)
        setResult(RESULT_OK, intent)
        finish()
    }

}
