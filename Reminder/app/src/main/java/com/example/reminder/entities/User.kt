package com.example.reminder.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "User")
class User(
    val name_user: String,
    val last_name_user: String,
    val email_user: String,
    val password: String,
    @PrimaryKey(autoGenerate = true)
    var id_User: Int = 0,
    ):Serializable {

}