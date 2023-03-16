package com.example.reminder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.example.reminder.R
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.SubTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndividualSubtaskActivity : AppCompatActivity() {
    var lvl_priority_subtask = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_subtask)
        //receive intent from SubTaskActivity
        val id_task = intent.getIntExtra("id_task",0)

        //database
        val database = AppDataBase.getDatabase(this)

        //Extract data from forms
        val buttonSaveSubtask = findViewById<Button>(R.id.id_button_save_sub_task)
        buttonSaveSubtask.setOnClickListener {

            val name_sub_task = findViewById<EditText>(R.id.name_sub_task).text.toString()
            val description_sub_task = findViewById<EditText>(R.id.description_sub_task).text.toString()
            val status_sub_task = "Pending"
            val subtask = SubTask(id_task,name_sub_task,description_sub_task,lvl_priority_subtask,status_sub_task)

            CoroutineScope(Dispatchers.IO).launch {
                database.subtask().insertAll(subtask)
                this@IndividualSubtaskActivity.finish()
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