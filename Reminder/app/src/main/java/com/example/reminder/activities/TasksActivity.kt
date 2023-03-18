package com.example.reminder.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.adapters.TaskAdapter
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.Task
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TasksActivity : AppCompatActivity() {

    private var listTask = emptyList<Task>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)

        // Receive intent from LoginActivity
        val id_user = intent.getIntExtra("id_usr", 0)

        //set date
        val timestamp = ZonedDateTime.now(ZoneId.of("America/Guayaquil"))
            .format(DateTimeFormatter.ofPattern("dd-MM-yyy"))
        findViewById<TextView>(R.id.date_filter).text = timestamp

        // Task list
        val database = AppDataBase.getDatabase(this)
        val list = findViewById<ListView>(R.id.task_list_view)
        database.task().getTaskbyUser(id_user).observe(this, Observer {
            listTask = it
            val taskAdapter = TaskAdapter(this, listTask)
            list.adapter = taskAdapter
        })

        list.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SubTasksActivity::class.java)
            intent.putExtra("id_task", listTask[position].id_task)
            startActivity(intent)
        }

        val buttonPending = findViewById<Button>(R.id.id_button_pending)
        buttonPending.setOnClickListener {
            database.task().getTaskbyStatus(id_user, "Pending").observe(this, Observer {
                listTask = it
                val taskAdapter = TaskAdapter(this, listTask)
                list.adapter = taskAdapter
            })
        }

        val buttonCompleted = findViewById<Button>(R.id.id_button_completed)
        buttonCompleted.setOnClickListener {
            database.task().getTaskbyStatus(id_user, "Completed").observe(this, Observer {
                listTask = it
                val taskAdapter = TaskAdapter(this, listTask)
                list.adapter = taskAdapter
            })
        }

        val buttonOverdue = findViewById<Button>(R.id.id_button_overdue)
        buttonOverdue.setOnClickListener {
            database.task().getTaskbyStatus(id_user, "Overdue").observe(this, Observer {
                listTask = it
                val taskAdapter = TaskAdapter(this, listTask)
                list.adapter = taskAdapter
            })
        }

        val buttonAddTask = findViewById<Button>(R.id.id_button_add_task)
        buttonAddTask.setOnClickListener {
            val intent = Intent(this, IndividualTaskActivity::class.java)
            intent.putExtra("id_usr", id_user)
            startActivity(intent)
        }

    }
}
