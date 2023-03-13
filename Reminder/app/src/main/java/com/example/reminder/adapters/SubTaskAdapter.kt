package com.example.reminder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.reminder.R
import com.example.reminder.entities.SubTask

class SubTaskAdapter (
    private val context: Context,
    private val subTaskList: List<SubTask>,
    ) : ArrayAdapter<SubTask>(context,0, subTaskList){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_view,parent,false)
        val subTask = subTaskList[position]

        layout.findViewById<TextView>(R.id.name_task).text = subTask.name_sub_task
        layout.findViewById<TextView>(R.id.description_task).text = subTask.description_sub_task

        return layout
    }
}