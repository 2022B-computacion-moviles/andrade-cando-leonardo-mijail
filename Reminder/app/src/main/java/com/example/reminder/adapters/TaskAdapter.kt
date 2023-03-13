package com.example.reminder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.reminder.R
import com.example.reminder.entities.Task

class TaskAdapter (
    private val context: Context,
    private val taskList: List<Task>
    ): ArrayAdapter<Task>(context,0,taskList) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        val task = taskList[position]

        layout.findViewById<TextView>(R.id.name_task).text = task.name_task
        layout.findViewById<TextView>(R.id.description_task).text = task.description_task
        layout.findViewById<TextView>(R.id.date_task).text = task.date_task

        return layout
    }
}