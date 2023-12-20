package com.example.mypersonalcolor.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonalcolor.R
import com.example.mypersonalcolor.model.ColorPalette

class ColorPaletteAdapter(private var colorPalettes: List<ColorPalette>)
    : RecyclerView.Adapter<ColorPaletteAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvColorName: TextView = view.findViewById(R.id.tvColorName)
        private val tvColorHex: TextView = view.findViewById(R.id.tvColorHex)
        private val colorPreview: View = view.findViewById(R.id.colorPreview)

        fun bind(colorPalette: ColorPalette) {
            tvColorName.text = colorPalette.colorName
            tvColorHex.text = colorPalette.colorHex
            colorPreview.setBackgroundColor(Color.parseColor(colorPalette.colorHex))
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_color_palette, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(colorPalettes[position])
    }

    override fun getItemCount(): Int = colorPalettes.size

    fun updateData(newColorPalettes: List<ColorPalette>) {
        colorPalettes = newColorPalettes
        notifyDataSetChanged()
    }
}
