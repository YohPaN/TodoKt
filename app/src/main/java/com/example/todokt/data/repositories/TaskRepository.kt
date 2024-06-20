package com.example.todokt.data.repositories

import com.example.todokt.data.dao.TaskDao
import com.example.todokt.data.models.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: Flow<List<Task>> = taskDao.getAll()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateChecked(id: Int, checked: Boolean) {
        taskDao.updateChecked(id, !checked)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}