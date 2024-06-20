package com.example.todokt.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.todokt.data.models.Task
import com.example.todokt.viewModels.TaskViewModel
import androidx.compose.ui.Modifier


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Task(taskViewModel: TaskViewModel, task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {},
                onLongClick = { taskViewModel.taskToDelete = task.id }
            ),
    ) {
        Checkbox(
            checked = task.checked,
            onCheckedChange = { taskViewModel.updateChecked(task) }
        )
        Text(
            text = "${task.title}",
            modifier = Modifier.padding(15.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            if(task.id === taskViewModel.taskToDelete) {
                TextButton(onClick = { taskViewModel.delete(task) }) {
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = "delete",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}