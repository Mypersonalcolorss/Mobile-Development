package com.example.mypersonalcolor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize FirebaseAuth
        auth = FirebaseAuth.getInstance()

        val view = inflater.inflate(R.layout.fragment_auth, container, false)

        val emailEditText = view.findViewById<EditText>(R.id.etEmail)
        val passwordEditText = view.findViewById<EditText>(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)
        val registerTextView = view.findViewById<TextView>(R.id.tvRegister)
        val googleSignInButton = view.findViewById<Button>(R.id.btnGoogleSignIn)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Login successful, navigate to the next fragment or activity
                        // For example, navigate to the PersonalColorAnalysisActivity
                        startActivity(Intent(context, DashboardActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        task.exception?.message?.let {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(context, "Please enter email and password.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        registerTextView.setOnClickListener {
            // Handle navigation to registration screen
            // Replace 'RegistrationActivity::class.java' with your actual registration activity
            startActivity(Intent(context, RegisterActivity::class.java))
        }

        googleSignInButton.setOnClickListener {
            // Configure Google Sign In
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(context, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                val user = auth.currentUser
                // For example, navigate to the PersonalColorAnalysisActivity
                startActivity(Intent(context, DashboardActivity::class.java))
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(context, "Authentication Failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance(): AuthFragment {
            return AuthFragment()
        }

        private const val RC_SIGN_IN = 100
    }
}