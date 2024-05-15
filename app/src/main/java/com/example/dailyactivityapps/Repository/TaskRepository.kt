package com.example.dailyactivityapps.Repository

import com.example.dailyactivityapps.data.doo.TaskDao
import com.example.dailyactivityapps.data.entities.Tags
import com.example.dailyactivityapps.data.entities.TaskWithTagLists
import com.example.dailyactivityapps.data.entities.Tasks
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor( private val taskDao: TaskDao) {

    suspend fun insertTask(tasks: Tasks){
        taskDao.addTask(tasks)
    }
    suspend fun deleteTask(tasks: Tasks){
        taskDao.deleteTask(tasks)
    }
     fun getAllTasks(): Flow<List<Tasks>>{
       return taskDao.getAllTasks()
    }

    suspend fun insertTag(tags: Tags){
        taskDao.upsertTag(tags)
    }
    suspend fun deleteTag(tags: Tags){
        taskDao.deleteTag(tags)
    }
    fun getTagsWithTask(tagName : String): Flow<List<TaskWithTagLists>>{
        return taskDao.getTagsWithTask(tagName)
    }
    fun getAllTags(): Flow<List<Tags>>{
        return taskDao.getAllTags()
    }
}