package com.example.reminder.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IndividualTaskActivity : AppCompatActivity() {
    var lvl_priority = 0
    var tag_task = ""
    var id_task = 0
    var status_task = "Pending"

    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_task)
        //receive intent from TaskActivity
        var id_user = intent.getIntExtra("id_usr",0)
        //initialization id task for update and delete
        var id_task_update: Int? = null



        //database
        val database = AppDataBase.getDatabase(this)

        if(intent.hasExtra("id_task_update")){
            //receive intent from SubTaskActivity - Update
            id_task_update = intent.getIntExtra("id_task_update",0)
            database.task().getTaskByID(id_task_update).observe(this, Observer {
                task = it
                findViewById<TextView>(R.id.name_task).text = task.name_task
                findViewById<TextView>(R.id.description_task).text = task.description_task
                findViewById<TextView>(R.id.date_task).text = task.date_task
                when (task.lvl_priority_task) {
                    1 -> {
                        findViewById<RadioButton>(R.id.radioButton_p1).isChecked = true
                    }
                    2 -> {
                        findViewById<RadioButton>(R.id.radioButton_p2).isChecked = true
                    }
                    3 -> {
                        findViewById<RadioButton>(R.id.radioButton_p3).isChecked = true
                    }
                    4 -> {
                        findViewById<RadioButton>(R.id.radioButton_p4).isChecked = true
                    }
                }
                when (task.tag_task) {
                    "Job" -> {
                        findViewById<RadioButton>(R.id.radioButton_l1).isChecked = true
                    }
                    "School" -> {
                        findViewById<RadioButton>(R.id.radioButton_l2).isChecked = true
                    }
                    "Family" -> {
                        findViewById<RadioButton>(R.id.radioButton_l3).isChecked = true
                    }
                    "Extra" -> {
                        findViewById<RadioButton>(R.id.radioButton_l4).isChecked = true
                    }
                }
                when (task.status_task) {
                    "Completed" -> {
                        findViewById<CheckBox>(R.id.checkBox_task).isChecked = true
                    }

                }
                id_user = task.id_User
            })
        }


        //Extract data from forms
        val buttonSaveTask = findViewById<Button>(R.id.id_button_save_task)
        buttonSaveTask.setOnClickListener{
            val name_task = findViewById<TextView>(R.id.name_task).text.toString()
            val description_task = findViewById<TextView>(R.id.description_task).text.toString()
            val date_task = findViewById<TextView>(R.id.date_task).text.toString()


            val task = Task(id_user,name_task,description_task,date_task, lvl_priority,tag_task,status_task)

            //Toast.makeText(this,name_task+description_task+date_task+id_user,Toast.LENGTH_SHORT).show()

            if(id_task_update == null){
                //insert
                CoroutineScope(Dispatchers.IO).launch {
                    database.task().insertAll(task)
                    this@IndividualTaskActivity.finish()
                }
            }else{
                //update
                //Toast.makeText(this,"UPDATE",Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {
                    task.id_task = id_task_update
                    database.task().update(task)
                    this@IndividualTaskActivity.finish()
                }
            }
        }
    }

    fun setPriority(v: View?): Int {
        if (findViewById<RadioButton>(R.id.radioButton_p1).isChecked){
            lvl_priority = 1
        }else if (findViewById<RadioButton>(R.id.radioButton_p2).isChecked){
            lvl_priority = 2
        }else if (findViewById<RadioButton>(R.id.radioButton_p3).isChecked){
            lvl_priority = 3
        }else if (findViewById<RadioButton>(R.id.radioButton_p4).isChecked){
            lvl_priority = 4
        }
        return lvl_priority
    }

    fun setTag(v: View?): String {
        if (findViewById<RadioButton>(R.id.radioButton_l1).isChecked){
            tag_task = "Job"
        }else if (findViewById<RadioButton>(R.id.radioButton_l2).isChecked){
            tag_task = "School"
        }else if (findViewById<RadioButton>(R.id.radioButton_l3).isChecked){
            tag_task = "Family"
        }else if (findViewById<RadioButton>(R.id.radioButton_l4).isChecked){
            tag_task = "Extra"
        }
        return tag_task
    }
    fun setStatus(v: View?): String {
        if (findViewById<CheckBox>(R.id.checkBox_task).isChecked){
            status_task = "Completed"
        }
        return status_task
    }
}