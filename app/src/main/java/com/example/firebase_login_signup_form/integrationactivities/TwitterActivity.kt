package com.example.firebase_login_signup_form.integrationactivities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_login_signup_form.DashboardFragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider

class TwitterActivity : AppCompatActivity() {

    lateinit var fAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fAuth = FirebaseAuth.getInstance()

        val provider = OAuthProvider.newBuilder("twitter.com")
        provider.addCustomParameter("lang", "eng")

        val pendingResultTask: Task<AuthResult>? = fAuth.pendingAuthResult
        if (pendingResultTask != null) {
            pendingResultTask.addOnSuccessListener {
                Log.d("success", "Login Successful")
                Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, DashboardFragment::class.java))
            }
                .addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    // Handle failure.
                }
        } else {

            try {
                fAuth.startActivityForSignInWithProvider(this, provider.build())
                    .addOnSuccessListener {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show()
                        Log.d("success", "Loggedin")
                        startActivity(Intent(this, DashboardFragment::class.java))
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        // Handle failure.
                    }
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                Log.d("ERROR", "${e.message}")
            }
        }
    }
}