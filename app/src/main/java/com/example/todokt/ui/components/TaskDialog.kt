package com.example.todokt.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import com.example.todokt.viewModels.TaskViewModel

@Composable
fun TaskDialog(taskViewModel: TaskViewModel) {
    AlertDialog(
        text = {
            TextField(
                value = taskViewModel.dialogTask,
                onValueChange = { taskViewModel.dialogTask = it }
            )
        },
        onDismissRequest = {},
        confirmButton = {
            Button(onClick = { taskViewModel.confirmTaskCreation() }) {
                Text("Confirmer")
            }

        },
        dismissButton = {
            Button(onClick = { taskViewModel.hideDialog() }) {
                Text("Annuler")
            }
        }
    )
}