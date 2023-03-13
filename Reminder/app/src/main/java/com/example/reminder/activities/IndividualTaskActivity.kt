package com.example.reminder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.reminder.R
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndividualTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_task)
        //recive intent from TaskActivity
        val id_user = intent.getIntExtra("id_usr",0)
        //database
        val database = AppDataBase.getDatabase(this)

        //Extract data from forms
        val buttonSaveTask = findViewById<Button>(R.id.id_button_save_task)
        buttonSaveTask.setOnClickListener{
            val id_User = id_user
            val name_task = findViewById<TextView>(R.id.name_task).text.toString()
            val description_task = findViewById<TextView>(R.id.description_task).text.toString()
            val date_task = findViewById<TextView>(R.id.date_task).text.toString()
            val status_task = "Pending"
            val task = Task(id_User,name_task,description_task,date_task, 1,"Trabajo",status_task)

            CoroutineScope(Dispatchers.IO).launch {
                database.task().insertAll(task)
                this@IndividualTaskActivity.finish()
            }
        }
    }
}