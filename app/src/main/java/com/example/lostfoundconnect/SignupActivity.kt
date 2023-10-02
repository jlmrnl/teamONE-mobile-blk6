package com.example.lostfoundconnect

import android.app.DownloadManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
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
            val txtGoToSignin = Intent(this, LoginActivity::class.java)

            // You can also pass data to the new activity if needed
            txtGoToSignin.putExtra("key", "value")

            // Start the new activity
            startActivity(txtGoToSignin)

        }

        btnSignup.setOnClickListener {
            val name = txtName.text.toString()
            val email = txtEmail.text.toString()
            val password = txtPassword.text.toString()
            val confirmPassword = txtConfirmPassword.text.toString()

            // Validate input
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                // Handle validation error (e.g., show a toast message)
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                // Handle password mismatch error (e.g., show a toast message)
                return@setOnClickListener
            }

            // Make API request
            val client = OkHttpClient()
            val requestBody = FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("password", password)
                .build()

            val request = Request.Builder()
                .url("YOUR_API_SIGNUP_ENDPOINT_URL")
                .post(requestBody)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    // Handle API call failure (e.g., show a toast message)
                }

                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body?.string()
                    val json = JSONObject(responseBody)

                    // Check the response status and handle accordingly
                    val success = json.getBoolean("success")
                    val message = json.getString("message")

                    if (success) {
                        // Signup successful, handle the success case (e.g., navigate to the next screen)
                    } else {
                        // Signup failed, handle the error case (e.g., show a toast message with the error message)
                    }
                }
            })
        }


    }
}
