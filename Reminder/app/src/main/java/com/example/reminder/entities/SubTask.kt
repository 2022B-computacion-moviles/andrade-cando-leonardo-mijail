package com.example.reminder.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity (tableName = "SubTask")
class SubTask(
    val id_task: Int,
    val name_sub_task: String,
    val description_sub_task:String,
    val lvl_priority_sub_task: Int,
    val status_sub_task:String,
    @PrimaryKey(autoGenerate = true)
    var id_subTarea: Int = 0
): Serializable {

}