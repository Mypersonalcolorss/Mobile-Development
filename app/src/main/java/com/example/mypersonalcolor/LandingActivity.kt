package com.example.mypersonalcolor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LandingActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        val adapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = adapter

        // Link the TabLayout and the ViewPager2 together
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Here you can customize the tab, but as we want just dots, we leave it empty
        }.attach()
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        private val screens: List<Fragment> = listOf(
            LandingScreenFragment.newInstance(R.drawable.landing_image_1, getString(R.string.quote_1)),
            LandingScreenFragment.newInstance(R.drawable.landing_image_2, getString(R.string.quote_2)),
            LandingScreenFragment.newInstance(R.drawable.landing_image_3, getString(R.string.quote_3)),
            AuthFragment.newInstance()
        )

        override fun getItemCount(): Int = screens.size

        override fun createFragment(position: Int): Fragment = screens[position]
    }
}