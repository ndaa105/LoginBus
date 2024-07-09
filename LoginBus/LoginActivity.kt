package com.example.busapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.busapp.data.AppDatabase

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db = AppDatabase.getDatabase(this)
        val userDao = db.userDao()

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val user = userDao.login(email, password)

            if (user != null) {
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("username", user.nama)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Username/Password salah!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
