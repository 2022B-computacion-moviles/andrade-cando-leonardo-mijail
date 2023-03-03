package com.example.reminder.database

import androidx.room.Insert
import androidx.room.Query
import com.example.reminder.entities.Task

interface TaskDao {

    @Query("SELECT * FROM USER, TASK WHERE USER.id_User = TASK.id_User AND USER.id_User =:id")
    fun getTaskbyUser(id:Int)

    @Insert
    fun insertAll(vararg : Task)
}