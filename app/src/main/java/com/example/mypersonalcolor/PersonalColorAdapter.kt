package com.example.mypersonalcolor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.mypersonalcolor.R

class PersonalColorAdapter(private val context: Context) : BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // Dummy data for the grid items
    private val images = arrayOf(
        R.drawable.ic_user, R.drawable.ic_dress,
        R.drawable.ic_styleguide, R.drawable.ic_avatar,
        R.drawable.ic_palette, R.drawable.ic_cosmetics
    )

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.grid_item_personal_color, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.imageViewItem)
        imageView.setImageResource(images[position])
        return view
    }
}
