package com.example.mypersonalcolor.model

data class DashboardFeature(
    val iconResId: Int,                // Resource ID for the feature's icon
    val name: String,                  // Name of the feature
    val isEnabled: Boolean,            // Indicates if the feature is enabled
    var isVisible: Boolean,            // Indicates if the feature should be visible
    val onClickAction: () -> Unit      // Click action for the feature
)

