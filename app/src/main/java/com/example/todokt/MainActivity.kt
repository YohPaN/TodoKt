package com.example.todokt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todokt.data.TaskDatabase
import com.example.todokt.data.repositories.TaskRepository
import com.example.todokt.ui.components.Task
import com.example.todokt.ui.components.TaskDialog
import com.example.todokt.viewModels.TaskViewModel
import com.example.todokt.ui.theme.ToDoKtTheme

class MainActivity : ComponentActivity() {
    private lateinit var database: TaskDatabase
    private lateinit var taskRepository: TaskRepository
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = TaskDatabase.getDatabase(this)
        taskRepository = TaskRepository(database.taskDao())
        taskViewModel = TaskViewModel(taskRepository)

        setContent {
            ToDoKtTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting("To Do", taskViewModel = taskViewModel)
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, taskViewModel: TaskViewModel) {
    val tasks by taskViewModel.allTasks.collectAsState(initial = emptyList())

    if(taskViewModel.openDialog) {
        TaskDialog(taskViewModel)
    }

    Column(Modifier.background(color = Color.LightGray)) {
        Text("$name", modifier = modifier
            .align(Alignment.CenterHorizontally)
            .padding(15.dp)
        )

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            for (task in tasks) {
                Task(taskViewModel = taskViewModel, task = task)
            }
            Button(
                onClick = { taskViewModel.openDialog = true },
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(15.dp)
            ) {
                Text("Add task")
            }
        }
    }

}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ToDoKtTheme {
//        Greeting("To Do")
//    }
//}
