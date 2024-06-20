package com.example.todokt.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todokt.data.models.Task
import com.example.todokt.data.repositories.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository): ViewModel() {
    val allTasks: Flow<List<Task>> = repository.allTasks

    var openDialog by mutableStateOf(false)
    var dialogTask by mutableStateOf("")
    var taskToDelete by mutableIntStateOf(-1)

    fun insert(task: Task) = viewModelScope.launch {
        repository.insert(task)
    }

    fun updateChecked(task: Task) = viewModelScope.launch {
        repository.updateChecked(task.id, task.checked)
    }

    fun delete(task: Task) = viewModelScope.launch {
        repository.delete(task)
        taskToDelete = -1
    }

    fun hideDialog() {
        dialogTask = ""
        openDialog = false
    }

    fun confirmTaskCreation() {
        if(dialogTask !== "") {
            insert(Task(title = dialogTask, checked = false))
        }
        hideDialog()
    }
}