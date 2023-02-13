package com.example.crud_sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SistemaPlanetario::class, Planeta::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sistemaPlanetario (): SistemaPlanetarioDao
    abstract fun planeta (): PlanetaDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase?=null

        fun getDatabase(context:Context):AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance

                return instance
            }

        }
    }



}