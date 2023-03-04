package com.example.reminder.database

import androidx.room.Insert
import androidx.room.Query
import com.example.reminder.entities.SubTask

interface SubTaskDao {

    @Query("SELECT * FROM USER, TASK, SUBTASK WHERE USER.id_User = TASK.id_User AND USER.id_User =:id AND SUBTASK.id_task=TASK.id_task")
    fun getSubTaskbyUser(id:Int)

    @Insert
    fun insertAll(vararg :SubTask)
}