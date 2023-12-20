package com.example.mypersonalcolor.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonalcolor.R
import com.example.mypersonalcolor.model.DashboardFeature

class DashboardAdapter(private var features: List<DashboardFeature>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the layout for each item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_full_width_feature, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feature = features[position]
        holder.ivFeatureIcon.setImageResource(feature.iconResId)
        holder.tvFeatureName.text = feature.name
        holder.ivLockIcon.visibility = if (feature.isEnabled) View.GONE else View.VISIBLE
        holder.itemView.setOnClickListener {
            if (feature.isEnabled) feature.onClickAction.invoke()
        }
        holder.bind(feature)
    }

    override fun getItemCount(): Int {
        // Return the size of the data list
        return features.size
    }

    fun updateFeatures(newFeatures: List<DashboardFeature>) {
        features = newFeatures
        notifyDataSetChanged()
    }

    fun updateData(newFeatures: List<DashboardFeature>) {
        this.features = newFeatures
        notifyDataSetChanged()
    }

    fun updateFeatureVisibility(isAnalysisCompleted: Boolean) {
        features.forEach { feature ->
            // Assuming 'isAnalysisCompleted' is a parameter in DashboardFeature
            if (feature.name == "Your Dress Recommendations") {
                feature.isVisible = isAnalysisCompleted
            }
            // Update for other features if necessary
        }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivFeatureIcon: ImageView = view.findViewById(R.id.ivFeatureIcon)
        val tvFeatureName: TextView = view.findViewById(R.id.tvFeatureName)
        val ivLockIcon: ImageView = view.findViewById(R.id.ivLockIcon)

        fun bind(feature: DashboardFeature) {
            ivFeatureIcon.setImageResource(feature.iconResId)
            tvFeatureName.text = feature.name
            ivLockIcon.visibility = if (feature.isEnabled) View.GONE else View.VISIBLE
            itemView.visibility = if (feature.isVisible) View.VISIBLE else View.GONE
        }
    }


}
