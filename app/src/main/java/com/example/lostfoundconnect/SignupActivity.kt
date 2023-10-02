package com.example.lostfoundconnect

import android.app.DownloadManager
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_signup)


        val txtName = findViewById<EditText>(R.id.tfSignupName)
        val txtEmail = findViewById<EditText>(R.id.tfSignupEmail)
        val txtPassword = findViewById<EditText>(R.id.tfSignupPw)
        val txtConfirmPassword = findViewById<EditText>(R.id.tfSignupConfirmPw)

        val txtGoToSignin = findViewById<TextView>(R.id.txtGoToSignin)
        val btnSignup = findViewById<Button>(R.id.btnSignup)

        // Set a click listener for the button
        txtGoToSignin.setOnClickListener {
            // Create an Intent to open the new activity
            val intent = Intent(this, LoginActivity::class.java)

            // You can also pass data to the new activity if needed
            intent.putExtra("key", "value")

            // Start the new activity
            startActivity(intent)
        }


        val apiUrl = "http://192.168.128.104:3000/auth/signup"  // Use 10.0.2.2 for accessing localhost from Android Emulator

        btnSignup.setOnClickListener {
            val name = txtName.text.toString().trim()
            val email = txtEmail.text.toString().trim()
            val password = txtPassword.text.toString().trim()
            val confirmPassword = txtConfirmPassword.text.toString().trim()

            // Validate input
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showToast("All fields are required")
                return@setOnClickListener
            }

            // Validate email format
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                txtEmail.error = "Invalid email address"
                return@setOnClickListener
            }

            // Validate password length
            if (password.length < 6) {
                txtPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            // Validate password match
            if (password != confirmPassword) {
                txtConfirmPassword.error = "Passwords do not match"
                return@setOnClickListener
            }

            // Clear errors if validation succeeds
            txtEmail.error = null
            txtPassword.error = null
            txtConfirmPassword.error = null

            // Make API request
            val client = OkHttpClient()
            val requestBody = FormBody.Builder()
                .add("name", name)
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
                            showToast("API Response: $responseBody")

                            // You can also pass data to the new activity if needed
                            // You can parse responseBody as JSON here if needed
                        }
                        201 -> {
                            // Handle resource created (success with new resource creation)
                            showToast("Resource created successfully: $responseBody")

                            // Navigate to LoginActivity
                            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                            startActivity(intent)
                            finish() // Close the current activity to prevent the user from coming back to the signup screen using the back button
                        }

                        204 -> {
                            // Handle success with no content (e.g., successful deletion)
                            showToast("Request processed successfully with no content")
                        }
                        400 -> {
                            // Handle bad request (client error) - validation errors, etc.
                            showToast("Bad request: $responseBody")
                        }
                        401 -> {
                            // Handle unauthorized access
                            showToast("Unauthorized: $responseBody")
                        }
                        403 -> {
                            // Handle forbidden access
                            showToast("Forbidden: $responseBody")
                        }
                        404 -> {
                            // Handle resource not found
                            showToast("Resource not found: $responseBody")
                        }
                        500 -> {
                            // Handle internal server error
                            showToast("Internal server error: $responseBody")
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
            Toast.makeText(this@SignupActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

}

