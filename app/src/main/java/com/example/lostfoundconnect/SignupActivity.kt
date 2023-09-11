package com.example.lostfoundconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_signup)

        val txtGoToSignin = findViewById<TextView>(R.id.txtGoToSignin)

        // Set a click listener for the button
        txtGoToSignin.setOnClickListener {
            // Create an Intent to open the new activity
            val txtGoToSignin = Intent(this, LoginActivity::class.java)

            // You can also pass data to the new activity if needed
            txtGoToSignin.putExtra("key", "value")

            // Start the new activity
            startActivity(txtGoToSignin)

        }
    }
}