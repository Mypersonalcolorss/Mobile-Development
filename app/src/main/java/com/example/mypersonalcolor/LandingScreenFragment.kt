package com.example.mypersonalcolor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

class LandingScreenFragment : Fragment() {

    companion object {
        private const val ARG_IMAGE_RES = "image_resource"
        private const val ARG_QUOTE = "quote"

        fun newInstance(@DrawableRes imageRes: Int, quote: String): LandingScreenFragment {
            val fragment = LandingScreenFragment()
            val args = Bundle()
            args.putInt(ARG_IMAGE_RES, imageRes)
            args.putString(ARG_QUOTE, quote)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_landing_screen, container, false)

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val quoteTextView: TextView = view.findViewById(R.id.quoteTextView)

        arguments?.let {
            imageView.setImageResource(it.getInt(ARG_IMAGE_RES))
            quoteTextView.text = it.getString(ARG_QUOTE)
        }

        return view
    }
}
