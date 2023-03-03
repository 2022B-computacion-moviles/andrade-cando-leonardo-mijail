package com.example.reminder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reminder.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg usr: User)
}