package com.example.dailyactivityapps.prsentation.Screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dailyactivityapps.prsentation.Screens.task.TaskViewModel

@Composable
fun HomeScreen(viewModel: TaskViewModel , navController: NavHostController){
    LazyColumn(modifier = Modifier.fillMaxSize()) {

        item {

            val result = viewModel.tasks.collectAsState(null)
            val tags = viewModel.tags.collectAsState(null)
            val tasksByTag = viewModel.tasksbyTags.collectAsState(null)
            Text(text = result.value.toString())
            Spacer(modifier = Modifier.padding(vertical = 12.dp) )
            Text(text = tasksByTag.value.toString())
            Spacer(modifier = Modifier.padding(vertical = 12.dp) )
        }

    }
}