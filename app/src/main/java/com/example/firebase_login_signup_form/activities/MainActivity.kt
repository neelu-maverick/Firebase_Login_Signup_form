package com.example.firebase_login_signup_form.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase_login_signup_form.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

    }
}
