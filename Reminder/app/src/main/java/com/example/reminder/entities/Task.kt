package com.example.reminder.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Task")
class Task (
    val id_User: Int,
    val name_task: String,
    val description_task: String,
    val date_task: String,
    val lvl_priority_task: Int,
    val tag_task: String,
    val status_task: String,
    @PrimaryKey(autoGenerate = true)
    var id_task: Int = 0
    ):Serializable {
}