package com.example.reminder.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.reminder.R
import com.example.reminder.database.AppDataBase
import com.example.reminder.entities.SubTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IndividualSubtaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_subtask)
        //id_task

        //database
        val database = AppDataBase.getDatabase(this)

        //Extract data from forms
        val buttonSaveSubtask = findViewById<Button>(R.id.id_button_save_subtask)
        buttonSaveSubtask.setOnClickListener {
            val id_task = 1
            val name_sub_task = findViewById<EditText>(R.id.name_subtask).text.toString()
            val description_sub_task = findViewById<EditText>(R.id.description_subtask).text.toString()
            val status_sub_task = "Pending"
            val subtask = SubTask(id_task,name_sub_task,description_sub_task,1,status_sub_task)

            CoroutineScope(Dispatchers.IO).launch {
                database.subtask().insertAll(subtask)
                this@IndividualSubtaskActivity.finish()
            }
        }

    }
}