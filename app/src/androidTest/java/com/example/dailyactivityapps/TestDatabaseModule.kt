package com.example.dailyactivityapps

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dailyactivityapps.data.database.EventsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent :: class)
object TestDatabaseModule {
    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(
            context, EventsDatabase:: class.java
        ).allowMainThreadQueries().build()
}