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

        val buttonSetPriority1 = findViewById<Button>(R.id.btnPriority1)
        val buttonSetPriority2 = findViewById<Button>(R.id.btnPriority2)
        val buttonSetPriority3 = findViewById<Button>(R.id.btnPriority3)
        val buttonSetPriority4 = findViewById<Button>(R.id.btnPriority4)

        buttonSetPriority1.setOnClickListener {
            val lvl_priority_task = 1
        }

        buttonSetPriority2.setOnClickListener {
            val lvl_priority_task = 2
        }

        buttonSetPriority3.setOnClickListener {
            val lvl_priority_task = 3
        }

        buttonSetPriority4.setOnClickListener {
            val lvl_priority_task = 4
        }

        val buttonSetTag1 = findViewById<Button>(R.id.btnLabel1)
        val buttonSetTag2 = findViewById<Button>(R.id.btnLabel2)
        val buttonSetTag3 = findViewById<Button>(R.id.btnLabel3)
        val buttonSetTag4 = findViewById<Button>(R.id.btnLabel4)

        buttonSetTag1.setOnClickListener {
            val tag_task = "Trabajo"
        }

        buttonSetTag2.setOnClickListener {
            val tag_task = "Escuela"
        }

        buttonSetTag3.setOnClickListener {
            val tag_task = "Familia"
        }

        buttonSetTag4.setOnClickListener {
            val tag_task = "Extras"
        }

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