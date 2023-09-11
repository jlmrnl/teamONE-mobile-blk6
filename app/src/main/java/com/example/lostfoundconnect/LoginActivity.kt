package com.example.lostfoundconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)

        val txtGoToForgotPW = findViewById<TextView>(R.id.txtForgotPw)

        // Set a click listener for the button
        txtGoToForgotPW.setOnClickListener {
            // Create an Intent to open the new activity
            val txtGoToForgotPW = Intent(this, ForgotPasswordActivity::class.java)

            // You can also pass data to the new activity if needed
            txtGoToForgotPW.putExtra("key", "value")

            // Start the new activity
            startActivity(txtGoToForgotPW)

        }
    }
}