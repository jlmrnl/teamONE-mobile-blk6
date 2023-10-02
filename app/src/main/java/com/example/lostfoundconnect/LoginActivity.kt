package com.example.lostfoundconnect

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

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

        val btnLogin = findViewById<Button>(R.id.btnSignin)
        btnLogin.setOnClickListener {
            // Get references to the email and password EditText fields
            val emailEditText = findViewById<EditText>(R.id.tfSigninEmail)
            val passwordEditText = findViewById<EditText>(R.id.tfSigninPw)

            // Get user input from the EditText fields
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Validate input
            if (email.isEmpty()) {
                emailEditText.error = "This field is required"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                passwordEditText.error = "Enter your password"
                return@setOnClickListener
            }
            if (password.length < 6) {
                passwordEditText.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            // Make API request
            val apiUrl = "http://192.168.128.104:3000/auth/signin"
            val client = OkHttpClient()
            val requestBody = FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build()

            val request = Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // Handle API call failure (e.g., show a toast message)
                    showToast("Failed to connect to the server")
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string()

                    // Handle different status codes
                    when (response.code) {
                        200 -> {
                            // Handle successful response
                            val jsonResponse = JSONObject(responseBody)
                            val message = jsonResponse.getString("message")
                            val name = jsonResponse.getString("name")
                            showToast("Login successful. Welcome, $name!\n$message")

                            // You can also navigate to another activity here if needed
                            val intent = Intent(this@LoginActivity, SessionActivity::class.java)
                            startActivity(intent)
                        }
                        401 -> {
                            // Handle unauthorized access
                            showToast("Invalid email or password")
                        }
                        else -> {
                            // Handle other status codes if needed
                            showToast("Unexpected error occurred: $responseBody")
                        }
                    }
                }
            })
        }


        }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
        }

}

}