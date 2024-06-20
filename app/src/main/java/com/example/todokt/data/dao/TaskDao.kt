package com.example.todokt.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.todokt.data.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): Flow<List<Task>>

    @Insert
    suspend fun insert(task: Task)

    @Query("UPDATE tasks SET checked = :checked WHERE id = :id")
    suspend fun updateChecked(id: Int, checked: Boolean)

    @Delete
    suspend fun delete(task: Task)
}
