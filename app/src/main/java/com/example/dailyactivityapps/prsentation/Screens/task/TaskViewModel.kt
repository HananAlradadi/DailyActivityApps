package com.example.dailyactivityapps.prsentation.Screens.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyactivityapps.Repository.TaskRepository
import com.example.dailyactivityapps.data.entities.Tags
import com.example.dailyactivityapps.data.entities.TaskType
import com.example.dailyactivityapps.data.entities.Tasks
import kotlinx.coroutines.launch
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val tasks = taskRepository.getAllTasks()
    val tags = taskRepository.getAllTags()
    val tasksbyTags = taskRepository.getTagsWithTask("Personal")

    init {
        viewModelScope.launch {
            taskRepository.insertTag(
                Tags(
                    "Work" ,
                    "color",
                    "Personal"
)
            )
            taskRepository.insertTag(
            Tags(
                "Work" ,
                "color",
                "Personal"
            )
            )
            taskRepository.insertTask(
                Tasks(
                    title = "title" ,
                    description = "description",
                    data = "2022-02-02",
                    taskType = TaskType.OnGoing.type ,
                    timeFrom =  "10:20" ,
                    timeTo = "12:10",
                    tagName = "work"
                )
            )
            taskRepository.insertTask(
                Tasks(
                    title = "title" ,
                    description = "description",
                    data = "2022-02-02",
                    taskType = TaskType.OnGoing.type ,
                    timeFrom =  "10:20" ,
                    timeTo = "12:10",
                    tagName = "work"
                )
            )
        }
    }
}