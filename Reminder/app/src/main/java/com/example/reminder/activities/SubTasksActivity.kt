package com.example.reminder.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.adapters.SubTaskAdapter
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.SubTask
import com.example.reminder.entities.Task

class SubTasksActivity : AppCompatActivity() {

    private var id_sub_task = 0
    private var listSubTask = emptyList<SubTask>()
    private lateinit var task: Task
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_tasks)

        //receive intent from LoginActivity
        val id_task = intent.getIntExtra("id_task", 0)
        val database = AppDataBase.getDatabase(this)

        //show info task
        database.task().getTaskByID(id_task).observe(this, Observer {
            task = it
            findViewById<TextView>(R.id.name_task_father_list_subtask).text = task.name_task
            findViewById<TextView>(R.id.description_task2).text = task.description_task
            findViewById<TextView>(R.id.tv_tag).text = task.tag_task
            findViewById<TextView>(R.id.date_task2).text = task.date_task

            when (task.lvl_priority_task) {
                1 -> {
                    findViewById<View>(R.id.priority_boton_task_subtasks).background = resources.getDrawable(R.drawable.background_task_priority1)
                }
                2 -> {
                    findViewById<View>(R.id.priority_boton_task_subtasks).background = resources.getDrawable(R.drawable.background_task_priority2)
                }
                3 -> {
                    findViewById<View>(R.id.priority_boton_task_subtasks).background = resources.getDrawable(R.drawable.background_task_priority3)
                }
                4 -> {
                    findViewById<View>(R.id.priority_boton_task_subtasks).background = resources.getDrawable(R.drawable.background_task_priority4)
                }
            }
        })

        //SubTasks list
        val list = findViewById<ListView>(R.id.sub_task_list_view)
        database.subtask().getSubTaskbyTask(id_task).observe(this, Observer {
            listSubTask = it
            val subtaskAdapter = SubTaskAdapter(this, listSubTask)
            list.adapter = subtaskAdapter
        })

        val buttonAddSubTask = findViewById<Button>(R.id.id_button_add_sub_task)
        buttonAddSubTask.setOnClickListener {
            val intent = Intent(this, IndividualSubtaskActivity::class.java)
            intent.putExtra("id_task", id_task)
            startActivity(intent)
        }

        val buttonEditTask = findViewById<ImageButton>(R.id.edit_task_list_subtask)
        buttonEditTask.setOnClickListener {
            val intent = Intent(this, IndividualTaskActivity::class.java)
            intent.putExtra("id_task", id_task)
            startActivity(intent)
        }

    }
}