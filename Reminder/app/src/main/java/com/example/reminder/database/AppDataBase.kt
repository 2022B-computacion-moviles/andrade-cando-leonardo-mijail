package com.example.reminder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reminder.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDataBase :RoomDatabase (){
    abstract fun user (): UserDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase?= null

        fun getDatabase(context: Context):AppDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "reminder-db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance

                return instance
            }
        }
    }

}