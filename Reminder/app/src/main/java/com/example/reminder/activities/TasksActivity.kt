package com.example.reminder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.reminder.R

class TasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        //recive intent from LoginActivity
        val id_user = intent.getIntExtra("id_usr",0)


        //select
        //programacion  //select id= usr

        val buttonAddTask = findViewById<Button>(R.id.id_button_add_task)
        buttonAddTask.setOnClickListener {
            val intent = Intent(this, IndividualTaskActivity::class.java)
            intent.putExtra("id_usr",id_user)
            startActivity(intent)
        }
    }
}