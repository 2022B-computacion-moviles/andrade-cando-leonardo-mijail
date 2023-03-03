package com.example.reminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.reminder.activities.SiginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSignin = findViewById<Button>(R.id.id_button_signin_ac)
        btnSignin.setOnClickListener {
            val intent = Intent(this,SiginActivity::class.java)
            startActivity(intent)
        }
    }
}