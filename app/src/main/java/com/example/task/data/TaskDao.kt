package com.example.task.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.task.model.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM task_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE id = :taskId")
    fun findTaskById(taskId: Int): LiveData<Task?>

}