package com.example.reminder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
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

        // Receive intent from LoginActivity
        val id_user = intent.getIntExtra("id_usr",0)

        val btn = findViewById<Button>(R.id.id_btn_test)

        // Task list
        val database = AppDataBase.getDatabase(this)
        val list = findViewById<ListView>(R.id.task_list_view)
        database.task().getTaskbyUser(id_user).observe(this, Observer {
            listTask = it
            val taskAdapter = TaskAdapter(this,listTask)
            list.adapter = taskAdapter
        })
        registerForContextMenu(list)

        list.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SubTasksActivity::class.java)
            intent.putExtra("id_task", listTask[position].id_task)
            startActivity(intent)
        }

        val buttonAddTask = findViewById<Button>(R.id.id_button_add_task)
        buttonAddTask.setOnClickListener {
            val intent = Intent(this, IndividualTaskActivity::class.java)
            intent.putExtra("id_usr",id_user)
            startActivity(intent)
        }
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llamamos las opciones del menu
        menuInflater.inflate(R.menu.menu, menu)

        //Obtener el id del ArraylistSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        id_task = id + 1
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.menu_edit ->{
                val intent = Intent(this,EditTaskActivity::class.java )
                intent.putExtra("id_task",listTask[id_task].id_task)
                startActivity(intent)
                return true
            }
            R.id.menu_delete ->{

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}