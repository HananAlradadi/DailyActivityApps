package com.example.dailyactivityapps.data.doo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.dailyactivityapps.data.entities.Tags
import com.example.dailyactivityapps.data.entities.TaskWithTagLists
import com.example.dailyactivityapps.data.entities.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Upsert
    suspend fun addTask(task: Tasks)

    @Delete
    suspend fun deleteTask(task: Tasks)

    @Query ("SELECT * From task_table")
    fun getAllTasks(): Flow<List<Tasks>>

    @Upsert
    suspend fun upsertTag(tag: Tags)

    @Delete
    suspend fun deleteTag(tag: Tags)

    @Query ("SELECT * From tags_table")
    fun getAllTags(): Flow<List<Tags>>

    @Query("SELECT * From tags_table where tag_name = :tagName " )
    fun getTagsWithTask(tagName : String) : Flow<List<TaskWithTagLists>>




}