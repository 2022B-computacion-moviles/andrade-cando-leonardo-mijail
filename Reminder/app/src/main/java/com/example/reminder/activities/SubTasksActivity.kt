package com.example.reminder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.adapters.SubTaskAdapter
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.SubTask

class SubTasksActivity : AppCompatActivity() {

    private var id_sub_task = 0
    private var listSubTask = emptyList<SubTask>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_tasks)

        //receive intent from LoginActivity
        val id_task = intent.getIntExtra("id_task",0)

        //SubTasks list
        val database = AppDataBase.getDatabase(this)
        val list = findViewById<ListView>(R.id.sub_task_list_view)
        database.subtask().getSubTaskbyTask(id_task).observe(this, Observer {
            listSubTask = it
            val subtaskAdapter = SubTaskAdapter(this,listSubTask)
            list.adapter = subtaskAdapter
        })



    }
}