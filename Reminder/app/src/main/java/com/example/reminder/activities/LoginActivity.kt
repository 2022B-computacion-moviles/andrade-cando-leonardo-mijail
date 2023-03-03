package com.example.reminder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var userLiveData: LiveData<User>
    private lateinit var dataBase: AppDataBase
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dataBase = AppDataBase.getDatabase(this)


        val btnLogin = findViewById<Button>(R.id.id_button_login_ini)
        btnLogin.setOnClickListener {
            val email = findViewById<TextView>(R.id.id_log_email).text.toString()
            val password = findViewById<TextView>(R.id.id_log_password).text.toString()
            userLiveData = dataBase.user().obtener(email)

            userLiveData.observe(this, Observer {
                user = it
                if(email == user.email_user && password == user.password){
                    val intent = Intent(this,TasksActivity::class.java)
                    startActivity(intent)
                }else{
                    val toast = Toast.makeText(this,"Credentials not fund", Toast.LENGTH_SHORT)
                    toast.show()
                }
            })
        }

    }
}