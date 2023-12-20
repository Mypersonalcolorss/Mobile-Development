package com.example.mypersonalcolor

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonalcolor.adapter.DashboardAdapter
import com.example.mypersonalcolor.model.DashboardFeature
import com.google.android.material.navigation.NavigationView

class DashboardResultActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        initToolbar()
        initNavigationDrawer()
        initUserProfileButton()
        initDashboard()
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun initUserProfileButton() {
        val userProfileButton: ImageView = findViewById(R.id.btnUserProfile)
        userProfileButton.setOnClickListener {
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDashboard() {
        recyclerView = findViewById(R.id.recyclerViewDashboard)
        refreshDashboardFeatures()
    }

    private fun getDashboardFeatures(): List<DashboardFeature> {
        val isAnalysisDone = getAnalysisCompleted()

        return listOf(
//            DashboardFeature(R.drawable.ic_user, "Analysis Your Face", true, true) { navigateToFeature1() },
            DashboardFeature(R.drawable.ic_dress, "Your Dress Recommendations", isAnalysisDone, isAnalysisDone) { navigateToFeature2() },
            DashboardFeature(R.drawable.ic_palette, "Your Color Palettes", isAnalysisDone, isAnalysisDone) { navigateToFeature4() },
            DashboardFeature(R.drawable.ic_avatar, "Coming Soon Feature", isAnalysisDone, isAnalysisDone) {  },
            DashboardFeature(R.drawable.ic_skincare, "Your MakeUp Recommendations", isAnalysisDone, isAnalysisDone) { navigateToFeature3() },
            DashboardFeature(R.drawable.ic_cs, "Coming Soon ChatBot", isAnalysisDone, isAnalysisDone) {  },
            // Add other features here
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

    private fun navigateToFeature2() {
        val intent = Intent(this, DressRecommendationsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFeature3() {
        val intent = Intent(this, MakeupRecommendationsActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFeature4() {
        val intent = Intent(this, ClassificationActivity::class.java)
        startActivity(intent)
    }
}

