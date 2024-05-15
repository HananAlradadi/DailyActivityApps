package com.example.dailyactivityapps

import androidx.test.filters.SmallTest
import com.example.dailyactivityapps.data.database.EventsDatabase
import com.example.dailyactivityapps.data.doo.TaskDao
import com.example.dailyactivityapps.data.entities.Tags
import com.example.dailyactivityapps.data.entities.TaskType
import com.example.dailyactivityapps.data.entities.Tasks
import kotlinx.coroutines.flow.first
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class TaskDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: EventsDatabase

    private lateinit var taskDao: TaskDao

    val task =  Tasks(
        taskId = 1,
        title = "title",
        description = "description",
        data = "2022-02-02",
        taskType = TaskType.OnGoing.type,
        timeFrom = "10:20",
        timeTo = "12:10",
        tagName = "Work"
    )

    @Before
    fun setup(){
        hiltRule.inject()
        taskDao =  database.taskDao()
    }
    @After
    fun tearDown(){
        database.close()
    }
    @Test
    fun insertTask() = runTest {
        taskDao.addTask(task)
        val allTasks = taskDao.getAllTasks().first()
        assert(allTasks.contains(task))
    }
    @Test
    fun deleteTask()= runTest {
        taskDao.addTask(task)
        taskDao.deleteTask(task)
        val allTasks = taskDao.getAllTasks().first()
        assert(!allTasks.contains(task))
    }
    @Test
    fun getAllTasks() = runTest {
        val task =  Tasks(
            title = "title",
            description = "description",
            data = java.time.LocalDate.now().toString(),
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"
        )
        val task2 =  Tasks(
            title = "title",
            description = "description",
            data = java.time.LocalDate.now().toString(),
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"
        )
        taskDao.addTask(task)
        taskDao.addTask(task2)
        val allTasks = taskDao.getAllTasks().first()
        assert(allTasks== listOf(task,task2))
    }
    @Test
    fun upsertTag()= runTest {
        val tag = Tags(
            "Personal",
            "color",
            ""
        )
        taskDao.upsertTag(tag)
        taskDao.deleteTag(tag)
        val allTags = taskDao.getAllTags().first()
        assert(!allTags.contains(tag))
        assert(allTags.isEmpty())
    }
    @Test
    fun getAlltags() = runTest {
        val tag = Tags(
            "Personal",
            "color",
            ""
        )
        val tag2 = Tags(
            "Work",
            "color",
            ""
        )
        taskDao.upsertTag(tag)
        taskDao.upsertTag(tag2)
        val allTags = taskDao.getAllTags().first()
        assert(allTags == listOf(tag,tag2))
    }

    @Test
    fun getTagsWithTask()= runTest {
        val tag = Tags(
            "Personal",
            "color",
            ""
        )
        val tag2 = Tags(
            "Work",
            "color",
            ""
        )
        val task =  Tasks(
            taskId = 1,
            title = "title",
            description = "description",
            data = java.time.LocalDate.now().toString(),
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"
        )
        val task2 =  Tasks(
            taskId = 2,
            title = "title",
            description = "description",
            data = java.time.LocalDate.now().toString(),
            taskType = TaskType.Cancelled.type,
            timeFrom = "10:20",
            timeTo = "12:10",
            tagName = "Work"
        )
    }
}