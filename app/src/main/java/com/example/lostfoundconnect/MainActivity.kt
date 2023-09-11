package com.example.lostfoundconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        val btnContinue = findViewById<Button>(R.id.btnMainContinue)

        // Set a click listener for the button
        btnContinue.setOnClickListener {
            // Create an Intent to open the new activity
            val signup = Intent(this, SignupActivity::class.java)

            // You can also pass data to the new activity if needed
            signup.putExtra("key", "value")

            // Start the new activity
            startActivity(signup)

        }
    }
}