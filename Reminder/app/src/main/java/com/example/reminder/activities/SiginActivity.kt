package com.example.reminder.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.reminder.R
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SiginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val dataBase = AppDataBase.getDatabase(this)
        val btnRegister = findViewById<Button>(R.id.id_button_signin)
        btnRegister.setOnClickListener{

            val name = findViewById<TextView>(R.id.id_sig_name).text.toString()
            val lastname = findViewById<TextView>(R.id.id_sig_lastname).text.toString()
            val email = findViewById<TextView>(R.id.id_sig_email).text.toString()
            val password = findViewById<TextView>(R.id.id_sig_password).text.toString()
            val verifypassword = findViewById<TextView>(R.id.id_sig_verificationpassword).text.toString()

            if (password == verifypassword){
                val usr = User(name, lastname,email,password)
                CoroutineScope(Dispatchers.IO).launch {
                    dataBase.user().insertAll(usr)
                    this@SiginActivity.finish()
                }
            }else{
                val toast = Toast.makeText(this,"Passwords do NOT match",Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }
}