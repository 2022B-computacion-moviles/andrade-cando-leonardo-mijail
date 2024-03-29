package com.example.reminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.reminder.entities.SubTask
import com.example.reminder.entities.User

@Dao
interface SubTaskDao {

    @Query("SELECT * FROM TASK, SUBTASK WHERE SUBTASK.id_task = TASK.id_task AND TASK.id_task = :id")
    fun getSubTaskbyTask(id:Int): LiveData<List<SubTask>>

    @Query("SELECT * FROM TASK, SUBTASK WHERE SUBTASK.id_task = TASK.id_task AND SUBTASK.id_subTask = :id")
    fun getSubTaskbyId(id: Int): LiveData<SubTask>

    @Insert
    fun insertAll(vararg :SubTask)

    @Update
    fun update(subTask: SubTask)

    @Delete
    fun delete(subTask: SubTask)
}