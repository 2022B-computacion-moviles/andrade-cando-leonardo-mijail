package com.example.reminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.Query
import com.example.reminder.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertAll(vararg usr: User)

    @Query("SELECT * FROM USER WHERE USER.email_user = :email")
    @Ignore
    fun getMail(email:String): LiveData<User>


}