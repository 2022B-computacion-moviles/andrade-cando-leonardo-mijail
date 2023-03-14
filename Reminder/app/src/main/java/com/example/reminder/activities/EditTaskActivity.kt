package com.example.reminder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.reminder.R

class EditTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_task)

        //receive intent from IndividualTaskActivity
        //val id_task =

        val buttonAddSubTask = findViewById<Button>(R.id.id_button_add_sub_task)
        buttonAddSubTask.setOnClickListener {
            val intent = Intent(this, IndividualSubtaskActivity::class.java)
            //intent.putExtra("id_task", id_task)
            startActivity(intent)
        }
    }
}