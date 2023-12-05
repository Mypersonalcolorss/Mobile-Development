package com.example.mypersonalcolor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_auth, container, false)

        val emailEditText = view.findViewById<EditText>(R.id.etEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)
        val registerTextView = view.findViewById<TextView>(R.id.tvRegister)
        val googleSignInButton = view.findViewById<Button>(R.id.btnGoogleSignIn)

        loginButton.setOnClickListener {
            // Handle login logic
        }

        registerTextView.setOnClickListener {
            // Handle navigation to registration screen
        }

        googleSignInButton.setOnClickListener {
            // Handle Google sign-in logic
        }

        return view
    }

    companion object {
        fun newInstance(): AuthFragment {
            return AuthFragment()
        }
    }
}
