package com.example.mypersonalcolor

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonalcolor.adapter.DashboardAdapter
import com.example.mypersonalcolor.model.DashboardFeature
import com.google.android.material.navigation.NavigationView

class DashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var featureCardLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        initToolbar()
        initFeatureCard()
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun initFeatureCard() {
        featureCardLayout = findViewById(R.id.featureCardLayout)
        featureCardLayout.setOnClickListener {
            navigateToFeature1()
        }
    }

    private fun getDashboardFeatures(): List<DashboardFeature> {
        val isAnalysisDone = getAnalysisCompleted()

        return listOf(
            DashboardFeature(R.drawable.ic_user, "Analysis Your Face", true, true) { navigateToFeature1() }
        )
    }

    private fun getAnalysisCompleted(): Boolean {
        val sharedPref = getSharedPreferences("MyPersonalColorPref", MODE_PRIVATE)
        return sharedPref.getBoolean("AnalysisCompleted", false)
    }

    private fun refreshDashboardFeatures() {
        val features = getDashboardFeatures()
        val adapter = DashboardAdapter(features)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }

    private val ANALYSIS_REQUEST_CODE = 1

    private fun navigateToFeature1() {
        val intent = Intent(this, FaceAnalysisActivity::class.java)
        startActivityForResult(intent, ANALYSIS_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ANALYSIS_REQUEST_CODE && resultCode == RESULT_OK) {
            refreshDashboardFeatures()
        }
    }
}
