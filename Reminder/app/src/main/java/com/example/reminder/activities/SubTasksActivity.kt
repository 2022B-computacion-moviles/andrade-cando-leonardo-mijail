package com.example.reminder.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.adapters.SubTaskAdapter
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.SubTask
import com.example.reminder.entities.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubTasksActivity : AppCompatActivity() {

    var id_subtask = 0
    private var listSubTask = emptyList<SubTask>()
    private lateinit var taskLiveData: LiveData<Task>
    private lateinit var task: Task
    private lateinit var database: AppDataBase
    @SuppressLint("UseCompatLoadingForDrawables")
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_tasks)

        //receive intent from LoginActivity
        val id_task = intent.getIntExtra("id_task", 0)
        database = AppDataBase.getDatabase(this)
        taskLiveData = database.task().getTaskByID(id_task)
        //delete
        var task_delete: Task? = null



        //show info task
        if(intent.hasExtra("task_delete")){
            //receive intent from SubTaskActivity - Update
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                task_delete = intent.getSerializableExtra("task_delete",Task::class.java)
            }
            database.task().getTaskByID(id_task).removeObservers(this)
            database.subtask().getSubTaskbyTask(id_task).removeObservers(this)

            CoroutineScope(Dispatchers.IO).launch {
                if (task_delete != null) {
                    database.task().delete(task_delete)
                   this@SubTasksActivity.finish()
                }
            }

        }else if(!intent.hasExtra("task_delete")){
            taskLiveData.observe(this, Observer {
                if(it == null){
                    this@SubTasksActivity.finish()
                }else{
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
                }

            })
        }

        //SubTasks list
        val list = findViewById<ListView>(R.id.sub_task_list_view)
        database.subtask().getSubTaskbyTask(id_task).observe(this, Observer {
            listSubTask = it
            val subtaskAdapter = SubTaskAdapter(this, listSubTask)
            list.adapter = subtaskAdapter
        })
        registerForContextMenu(list)

        val buttonAddSubTask = findViewById<Button>(R.id.id_button_add_sub_task)
        buttonAddSubTask.setOnClickListener {
            val intent = Intent(this, IndividualSubtaskActivity::class.java)
            intent.putExtra("id_task", id_task)
            startActivity(intent)
        }

        val buttonEditTask = findViewById<ImageButton>(R.id.edit_task_list_subtask)
        buttonEditTask.setOnClickListener {
            val intent = Intent(this, IndividualTaskActivity::class.java)
            intent.putExtra("id_task_update", id_task)
            startActivity(intent)
        }

        val buttonDeleteTask = findViewById<ImageButton>(R.id.delete_task_list_subtask)
        buttonDeleteTask.setOnClickListener {
            val intent = intent
            intent.putExtra("task_delete",task)
            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu,menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        id_subtask = id + 1
        //Toast.makeText(applicationContext,id_subtask.toString(),Toast.LENGTH_SHORT).show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.menu_edit->{
                val intent = Intent(this,IndividualSubtaskActivity::class.java )
                intent.putExtra("id_task_update",listSubTask[id_subtask].id_subTask)
                startActivity(intent)
                return true
            }
            R.id.menu_delete ->{
                CoroutineScope(Dispatchers.IO).launch {
                    database.subtask().delete(listSubTask[id_subtask])
                }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}