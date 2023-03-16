package com.example.reminder.activities

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_task)
        //receive intent from TaskActivity
        val id_user = intent.getIntExtra("id_usr",0)
        //database
        val database = AppDataBase.getDatabase(this)

        //Extract data from forms
        val buttonSaveTask = findViewById<Button>(R.id.id_button_save_task)
        buttonSaveTask.setOnClickListener{
            val id_User = id_user
            val name_task = findViewById<TextView>(R.id.name_task).text.toString()
            val description_task = findViewById<TextView>(R.id.description_task).text.toString()
            val date_task = findViewById<TextView>(R.id.date_task).text.toString()
            val status_task = "Pending"
            val task = Task(id_User,name_task,description_task,date_task, lvl_priority,tag_task,status_task)

            CoroutineScope(Dispatchers.IO).launch {
                database.task().insertAll(task)
                this@IndividualTaskActivity.finish()
            }
        }
    }

    fun setPriority(v: View?): Int {
        if (findViewById<RadioButton>(R.id.radioButton_p1).isChecked){
            lvl_priority = 1
            Toast.makeText(this, "lvl1", Toast.LENGTH_LONG).show();
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
}