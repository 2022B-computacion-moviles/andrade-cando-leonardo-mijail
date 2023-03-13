package com.example.reminder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.adapters.TaskAdapter
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.Task

class TasksActivity : AppCompatActivity() {

    private var id_task = 0
    private var listTask = emptyList<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        //receive intent from LoginActivity
        val id_user = intent.getIntExtra("id_usr",0)


      
        //lista de tareas
        val database = AppDataBase.getDatabase(this)
        val list = findViewById<ListView>(R.id.task_list_view)
        database.task().getTaskbyUser(id_user).observe(this, Observer {
            listTask = it
            val taskAdapter = TaskAdapter(this,listTask)
            list.adapter = taskAdapter
        })



        val buttonAddTask = findViewById<Button>(R.id.id_button_add_task)
        buttonAddTask.setOnClickListener {
            val intent = Intent(this, IndividualTaskActivity::class.java)
            intent.putExtra("id_usr",id_user)
            startActivity(intent)
        }
    }
}