package com.example.reminder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.example.reminder.R
import com.example.reminder.adapters.SubTaskAdapter
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.SubTask
import com.example.reminder.entities.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndividualSubtaskActivity : AppCompatActivity() {
    var lvl_priority_subtask = 0
    private lateinit var task: Task
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_subtask)
        //receive intent from SubTaskActivity
        val id_task = intent.getIntExtra("id_task",0)

        //initialization id task for update
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
            })
        }

        //Extract data from forms - Insert
        val buttonSaveSubtask = findViewById<Button>(R.id.id_button_save_sub_task)
        buttonSaveSubtask.setOnClickListener {

            val name_sub_task = findViewById<EditText>(R.id.name_sub_task).text.toString()
            val description_sub_task = findViewById<EditText>(R.id.description_sub_task).text.toString()
            val status_sub_task = "Pending"
            val subtask = SubTask(id_task,name_sub_task,description_sub_task,lvl_priority_subtask,status_sub_task)

            if(id_task_update == null){
                //insert
                CoroutineScope(Dispatchers.IO).launch {
                    database.subtask().insertAll(subtask)
                    this@IndividualSubtaskActivity.finish()
                }
            }else{
                //update
                CoroutineScope(Dispatchers.IO).launch {
                    database.subtask().update(subtask)
                    this@IndividualSubtaskActivity.finish()
                }
            }



        }
    }
    fun setPrioritySubTask(v: View?): Int {
        if (findViewById<RadioButton>(R.id.radio_Button_p1).isChecked){
            lvl_priority_subtask = 1
        }else if (findViewById<RadioButton>(R.id.radio_Button_p2).isChecked){
            lvl_priority_subtask = 2
        }else if (findViewById<RadioButton>(R.id.radio_Button_p3).isChecked){
            lvl_priority_subtask = 3
        }else if (findViewById<RadioButton>(R.id.radio_Button_p4).isChecked){
            lvl_priority_subtask = 4
        }
        return lvl_priority_subtask
    }
}