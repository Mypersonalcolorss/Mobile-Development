package com.example.mypersonalcolor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.mypersonalcolor.R
import com.example.mypersonalcolor.model.DressRecommendation
import com.squareup.picasso.Picasso

class DressRecommendationsAdapter(private var recommendations: List<DressRecommendation>)
    : RecyclerView.Adapter<DressRecommendationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dress_recommendation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recommendation = recommendations[position]
        holder.bind(recommendation)
    }

    fun updateData(newRecommendations: List<DressRecommendation>) {
        recommendations = newRecommendations
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = recommendations.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView: ImageView = view.findViewById(R.id.imageViewRecommendation)

        fun bind(recommendation: DressRecommendation) {
            // Load image using Picasso
            Picasso.get()
                .load(recommendation.imageUrl)
                .into(imageView)
        }
    }
}

