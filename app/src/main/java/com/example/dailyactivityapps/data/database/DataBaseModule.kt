package com.example.dailyactivityapps.data.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent :: class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : EventsDatabase {
        return EventsDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: EventsDatabase) = database.taskDao()

}