package com.example.dailyactivityapps.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailyactivityapps.data.doo.TaskDao
import com.example.dailyactivityapps.data.entities.Tasks
import com.example.dailyactivityapps.data.entities.Tags


@Database(entities = [Tasks::class , Tags :: class] , version = 1 , exportSchema = false)
abstract class EventsDatabase:RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {

        @Volatile
        private var INSTANCE : EventsDatabase? = null

        fun getDatabase(context : Context) : EventsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EventsDatabase::class.java,
                    "events_database"
                )
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}