package com.example.reminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.reminder.entities.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM USER, TASK WHERE USER.id_User = TASK.id_User AND USER.id_User =:id")
    fun getTaskbyUser(id:Int): LiveData<List<Task>>

    @Insert
    fun insertAll(vararg : Task)
    @Update
    fun update(task: Task)
    @Delete
    fun delete(task: Task)
}