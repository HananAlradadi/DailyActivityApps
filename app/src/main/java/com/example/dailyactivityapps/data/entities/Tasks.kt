package com.example.dailyactivityapps.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Tasks(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_ID")
    var taskId : Long? = null ,
    @ColumnInfo(name = "task_title")
    var title : String ,
    @ColumnInfo(name = "task_description")
    var description : String ,
    @ColumnInfo(name = "data")
    var data : String ,
    @ColumnInfo(name = "time_from")
    var timeFrom : String ,
    @ColumnInfo(name = "time_to")
    var timeTo : String ,
    @ColumnInfo(name = "task_type")
    var taskType : String ,
    @ColumnInfo(name = "task_tag_name")
    var tagName : String = "" )
enum class TaskType(val type : String){
    Pending("Pending"),
    OnGoing("On Going"),
    Cancelled("Cancelled"),
    Completed("Completed")
}